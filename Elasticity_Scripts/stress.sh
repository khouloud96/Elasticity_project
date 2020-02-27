#!/bin/bash

################## 	Get the public DNS of master node	##################
public_dns=$1

################## 	SSH Connection to the instance		##################
cd InstanceAccessKeys/
sudo chmod 400 privateKey.pem
scp -r -oStrictHostKeyChecking=no -i privateKey.pem ubuntu@$public_dns:~

############ 	Install stress and stress CPU-bound task	##################
ssh -i  "privateKey.pem"  ubuntu@$public_dns "sudo apt-get install stress; stress -c 2 -t 25s;"


