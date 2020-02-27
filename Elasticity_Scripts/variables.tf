############################################################################
#  Security Credentials  # Access Keys (Access Key ID and Secret Access Key)
############################################################################ 
variable "access_key" {

	description = " The AWS access key"
	########### Default value ##########
	default = "your_access_key"

}
variable "secret_key" {
	
	description = " The AWS secret key"
	########### Default value ##########
	default = "your_secret_key"
}
variable "region" {

	description = "AWS region on which we will setup the swarm cluster: Irelande"
	default = "eu-west-1"

}
variable "ami"  {

	description = "Amazon Linux AMI: Ubuntu Server 16.04 LTS"
	default = "ami-f90a4880"

}

variable "instance_type" {

	description = "Instance type"
	default = "t2.micro"

}
variable "nbWorker" {
	
	description = " number of the workers (from input file)"

}

#Path to the private key file
variable "key_path" {
	
	default = "private_key.pem"
	
}
variable "password" {
	
	default = "&123p"
	
}
