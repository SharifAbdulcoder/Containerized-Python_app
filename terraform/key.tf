resource "aws_key_pair" "docker-py" {
  key_name   = "docker-py"
  public_key = "${file("~/.ssh/id_rsa.pub")}"
}
