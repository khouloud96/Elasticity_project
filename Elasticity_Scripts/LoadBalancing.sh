#!/bin/bash

cd $1

# Get the public DNS of master node
public_dns=$(terraform output | grep "master.public_dns_ip"  | cut -d ' ' -f 3) 
echo "==================> Public DNS = "$public_dns

cd $1/InstanceAccessKeys/
sudo chmod 400 privateKey.pem
scp -r -oStrictHostKeyChecking=no -i privateKey.pem $1/app-compose.yml ubuntu@$public_dns:~

#Load balancing
printf "\n=================== Load balancing =======================\n"
ssh -i  "privateKey.pem"  ubuntu@$public_dns 'sudo usermod -aG docker ${USER}; sudo docker service ls -q > dkr_svcs && for i in `cat dkr_svcs`; do sudo docker service update "$i" --detach=false --force ; done'
