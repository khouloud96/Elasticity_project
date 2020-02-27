#!/bin/bash

####################################### " Undeploy Task " ##################################################
cd $1


terraform destroy -lock=false --force
