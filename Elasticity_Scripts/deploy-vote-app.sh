#!/bin/bash

cd $1

# Get the public DNS of master node
public_dns=$(terraform output | grep "master.public_dns_ip"  | cut -d ' ' -f 3) 

cd $1/InstanceAccessKeys/
sudo chmod 400 privateKey.pem
scp -r -oStrictHostKeyChecking=no -i privateKey.pem $1/app-compose.yml ubuntu@$public_dns:~


# Deploy Task
printf "\n\n=================== Deploy Application =======================\n"
ssh -i  "privateKey.pem"  ubuntu@$public_dns 'sudo docker stack deploy -c app-compose.yml myapp; sudo docker service ls; sudo docker ps; sudo docker node ls;'

# Removing the application
#printf "\n============ Removing the application ============ \n"
#ssh -i  "privateKey.pem"  ubuntu@$public_dns 'sudo docker stack rm myapp'











