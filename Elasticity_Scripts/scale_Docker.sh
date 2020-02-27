#!/bin/bash

cd $1

# Get the public DNS of master node
public_dns=$(terraform output | grep "master.public_dns_ip"  | cut -d ' ' -f 3) 

cd $1/InstanceAccessKeys/
sudo chmod 400 privateKey.pem
scp -r -oStrictHostKeyChecking=no -i privateKey.pem $1/app-compose.yml ubuntu@$public_dns:~

# Deployment of the stack
printf "\n============== Scaling the application ===================\n"
ssh -i  "privateKey.pem"  ubuntu@$public_dns "sudo docker service scale $2=$3;"

# List tasks
printf "\n============== Checking the services ===================\n"
ssh -i  "privateKey.pem"  ubuntu@$public_dns ' sudo docker service ls;'











