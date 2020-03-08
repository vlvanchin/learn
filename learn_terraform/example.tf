provider "aws" {
  access_key = "${var.access_key}"
  secret_key = "${var.secret_key}"
  region     = "${var.region}"
}

resource "aws_instance" "example" {
  ami        = "ami-0340a7ebf82eda150"
  instance_type = "t1.micro"
}
