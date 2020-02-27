output "master.public_ip" {
	
	value = "${aws_instance.docker_swarm_manager.public_ip}"   
}

output "master.public_dns_ip" {
	
	value = "${aws_instance.docker_swarm_manager.public_dns}"   
}

output "master.private_ip" {
	
	value = "${aws_instance.docker_swarm_manager.private_ip}"   
}

output "master.private_dns_ip" {
	
	value = "${aws_instance.docker_swarm_manager.private_dns}"   
}
