#!/bin/bash

cd $1

# Get the public ip of master node
public_ip=$(terraform output | grep "master.private_dns_ip"  | cut -d ' ' -f 3) 
echo $public_ip
