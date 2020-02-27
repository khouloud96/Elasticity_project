############################################################
		# Key pair #
############################################################
resource "aws_key_pair" "keyPair" {

	key_name = "EC2_KeyPair"
	public_key = "${file("InstanceAccessKeys/publicKey.pem")}"
}
