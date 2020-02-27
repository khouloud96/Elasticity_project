#!/bin/bash

cd $1

# Init Task
printf "\n\n=================== (Create infrastructure - Terraform): Initialization =======================\n"
terraform init

# Loading variables from a file
printf "\n\n=================== Loading variables from the file 'terraform.tfvars' =======================\n"
sudo terraform plan -var-file="terraform.tfvars"

#Apply terraform plan without interactive mode: auto-approve 
printf "\n\n=================== Apply terraform plan =======================\n"
terraform apply -auto-approve






