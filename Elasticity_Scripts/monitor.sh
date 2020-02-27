#!/bin/bash

####################################### " Monitoring Task " ##################################################
printf "=================== Monitoring Tasks =======================\n"
cd $1

public_dns=$(terraform output | grep "master.public_dns_ip"  | cut -d ' ' -f 3) 

echo $public_dns

cd $1/InstanceAccessKeys/

sudo chmod 400 privateKey.pem


sudo scp -r $1/MonitoringTask ubuntu@$public_dns:/home/ubuntu

sudo ssh -i  "privateKey.pem"  ubuntu@$public_dns 'cd /home/ubuntu/MonitoringTask;sudo docker stack deploy -c monitoringService-compose.yml myapp; sudo docker service ls'








