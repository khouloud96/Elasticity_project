############################################################
		# Security group #
############################################################


resource "aws_security_group"  "swarm_securityGroup" {

	name = "swarm_securityGroup"
	description = "Allow ssh and web access"
    	
	ingress {
		from_port = "0"
		to_port = "0"
		protocol = "-1"
		cidr_blocks = ["157.159.110.193/32"]
	}
	
	ingress {
		from_port = "0"
		to_port = "0"
		protocol = "-1"
		cidr_blocks = ["172.31.0.0/16"]
	}

	egress {
		from_port = "0"
		to_port = "0"
		protocol = "-1"
		cidr_blocks = ["0.0.0.0/0"]
	}

	tags {
		name = "allow_all"
	}
	
}


