#!/bin/bash

cd $1

# Get the public DNS of master node
public_dns=$(terraform output | grep "master.public_dns_ip"  | cut -d ' ' -f 3) 
echo $public_dns
