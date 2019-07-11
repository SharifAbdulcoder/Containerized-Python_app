resource "aws_instance" "web" {
  ami           = "${var.ami}"
  instance_type = "${var.instance_type}"
  count         = "${var.count}"
  key_name      = "${var.aws_key_pair.docker-py}"

  tags {
    Name       = "terraform-january-${var.Created_by}-${count.index}"
    Env        = "dev"
    Dept       = "${var.Dept}"
    Created_by = "${var.Created_by}"
  }
}
