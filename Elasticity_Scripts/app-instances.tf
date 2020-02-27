############################################################
# AWS # Setup the cloud provider "Amazon Web Services" (AWS)
############################################################

provider "aws" {

	access_key = "${var.access_key}"
	secret_key = "${var.secret_key}"
	region = "${var.region}"
	

}

############################################################
# Instances # Defining resources to create by terraform
############################################################

resource "aws_instance"  "docker_swarm_manager" {

	ami = "${var.ami}"
	instance_type= "${var.instance_type}"


	#Define securit_group to allow incoming or outcoming traffic from an EC2 instance
	security_groups = ["${aws_security_group.swarm_securityGroup.name}"]

	#key name is necessary to establish ssh connection to the EC2 instance
	key_name = "${aws_key_pair.keyPair.key_name}"

	#copying Docker-install-script.sh file
	provisioner "file" {
	 	source = "installScripts/Docker-DockerCompose-install-script.sh"
	  	destination = "/home/ubuntu/Docker-DockerCompose-install-script.sh"
	}
	


	#Define how to connect to instances EC2 (resources just created by terraform in AWS) via ssh protocol
	connection {
	    
	    # Connection type
	    type = "ssh"
	    
	    # The default username for our AMI
	    user = "ubuntu"
	    
	    # The path to our keyfile
	    private_key = "${file("InstanceAccessKeys/privateKey.pem")}" 
	    
	    password = "${var.password}"
	  }
	
	
	#Provisioner block is used to execute scripts on a local or remote machine 
	provisioner "remote-exec" {
		    inline = [

			#Install Docker
			"sudo chmod +x /home/ubuntu/Docker-DockerCompose-install-script.sh",
			"cd /home/ubuntu",			
			"./Docker-DockerCompose-install-script.sh",
			
			#Initialize Docker Swarm
			"sudo docker swarm init",
	     	        "sudo docker swarm join-token -q worker > /home/ubuntu/token",
			"sudo ls -l",
	  		 ] 
	  		}
	
	#Name of EC2 instance
	tags {
		#Instance name
		Name = "swarm-manager"
	}

}


resource "aws_instance" "docker_swarm_worker" {
	count = "${var.nbWorker}"
	ami = "${var.ami}"
	instance_type = "${var.instance_type}"

	
	#Define securit_group to allow incoming or outcoming traffic from an EC2 instance
	security_groups = ["${aws_security_group.swarm_securityGroup.name}"]


	#key name is necessary to establish ssh connection to the EC2 instance
	key_name = "${aws_key_pair.keyPair.key_name}"
	

	#copying Docker-install-script.sh file
	provisioner "file" {
	 
		source = "installScripts/Docker-install-script.sh"
	  	destination = "/home/ubuntu/Docker-install-script.sh"
	}

	#Define how to connect to instances EC2 (resources just created by terraform in AWS) via ssh protocol	
	connection {
	    
	    # Connection type
	    type = "ssh"
	    
	    # The default username for our AMI
	    user = "ubuntu"

	    # The path to our keyfile
	    private_key = "${file("InstanceAccessKeys/privateKey.pem")}" 

	    password = "${var.password}"
	  }


	
	provisioner "file" {
    		source = "InstanceAccessKeys/privateKey.pem"
    		destination = "/home/ubuntu/privateKey.pem"
	}

	
	#Provisioner block is used to execute scripts on a local or remote machine
	provisioner "remote-exec" {
		    inline = [
			  
			#Install Docker
			"sudo chmod +x /home/ubuntu/Docker-install-script.sh",
			"cd /home/ubuntu",
			"./Docker-install-script.sh",


			#Create swarm worker
			"sudo chmod 400 /home/ubuntu/privateKey.pem",
     			"sudo scp -o StrictHostKeyChecking=no -o NoHostAuthenticationForLocalhost=yes -o UserKnownHostsFile=/dev/null -i privateKey.pem ubuntu@${aws_instance.docker_swarm_manager.private_ip}:/home/ubuntu/token .",

			"sudo docker swarm join --token $(cat /home/ubuntu/token) ${aws_instance.docker_swarm_manager.private_ip}:2377"
			
			

		    ]
		  }

	
    #"depends_on" is useful to create the docker swarm manager before the docker swarm worker	
    depends_on = [
      "aws_instance.docker_swarm_manager"
    ]

	
	tags {
		#Instance name
		Name = "swarm-worker-${count.index}"
	}
}
