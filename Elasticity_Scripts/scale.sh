#!/bin/bash

cd $1

#Apply terraform plan without interactive mode: auto-approve 
printf "\n\n=================== Apply terraform plan (Scaling) =======================\n"
terraform apply -auto-approve





