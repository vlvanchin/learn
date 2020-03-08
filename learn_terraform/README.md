# Guide to Terraform

## Installation

* download the zip file from https://www.terraform.io

### verify installation

* run the following ` $ terraform `

## Setup Configuration, initialization and apply changes

* let's setup the work space for this exercise
* create a folder "first-terraform-example" and `cd` to this folder
* create a file *example.tf*  and add the following

```
provider "aws" {
   profile    = "default"
  region     = "us-east-1"
}

Reource "aws_instance" "example" {
  ami           = "ami-06963965"
  instance_type = "t2.micro"
}
```

please note that the ami are very specific to the regions. So for the singapore region... i will be using the following

```
  ami = "ami-06963965"
  instance_type = "t2.micro"

  ami = "ami-18e7417b"
  instance_type = "t2.micro"
```

> please make sure to have the aws configuration done in ~/.aws folder

## Initiallization

This is the command to run the new configuration

> $ terraform init

we should see no errors on running this above command

## Apply Changes

Run the following command for creating the resources in aws.

> $ terraform apply

* this will show the execution plan
* the output formation is similay to `git diff`
* this stage could have errors due to some code syntax
* if the plan is successful, then this is the time to pause or wait for approval...type 'yes' to proceed
* if *yes* then the resource is created with the *provider*
* you can logon to aws to verify the resources created

Now we can get the details of the resources by the following command

> $ terraform show

### provisioning

the EC2 installed does not have any additional software

## Destroy

The created resource can be destory using the following command

> $ terraform destroy

## Change infrastructure

here we try to change the the ami that is used in the previous step with out destroying

```
resource "aws_instance" "example" {
    ami           = "ami-18e7417b"
    instance_type = "t2.micro"
}
```
then again `terraform apply`

## Update the configuration (to create resouce dependency)

* here we will encounter more resources for the first time
* in terraform cfg can contain multiple resources, multiple resource types, multiple providers

### Assigning a Elastic IP

update the example.tf with the following

> resource "aws_eip" "ip" {
>   vpc = true
>   instance = aws_instance.example.id
> }

once configured, apply the changes

> $ terraform apply

### Implicit and Explicit Dependencies

#### Implicit dependency

```
resource "aws_eip" "ip" {
  vpc = true
  instance = aws_instance.example.id
}
```

* In the above example, the reference to the `aws_instance.example.id` has created and implicit dependency over the `aws_instance`  named `example`. This helps Terraform to find the correct order to create different resources.
* so the `aws_instance` must be created before `aws_eip`.
* this reference is done by _interpolation_ expression.

* sometimes the reference is not visible to terraform, hence the use of `depends_on` argument is used in the configuration.
* example, perhaps an application we will run on our EC2 instance expects to use a specific Amazon S3 bucket, but that dependency is configured inside the application code and thus not visible to Terraform. In that case, we can use depends_on to explicitly declare the dependency:

#### adding new s3 resource

```
resource "aws_s3_bucket" "example" {
  bucket = "vanchin-terraform-getting -started guide"
  acl = "private"
}

```

#### now create a explict link to aws_instance.example resource by modifying the resoure
```
resource "aws_instatnce" "example" {
  ami = "ami-xxxx"
  instance_type = "t2.micro"

  depends_on = [aws_s3_bucket.example]
}
```

## Provision

so far we used image based infrastructure...where the images are build in *Packer* but to do initial setup for instances... then Provisioners are used to upload files and run scripts and install or trigger softwares.

### Define Provisioner

Modify the resource block that defines the "example" EC2 instance as below:

```
  provisioner "local-exec" {
    command = "echo ${aws_instance.example.public_ip} > ip_address.txt"
  }
```

* note that the _ip_address.txt_ contains the public ip address.
* if the file is not generated, then just destroy the deployment and re-apply the plan.

### Graph

we can view the relationship graph of the resources, resource-type and the provider using the following command.

> $ terraform graph

Just copy the output to clipboard. Now visit the http://www.webgraphviz.com/ and paste the contents to the textarea and click *Generate Graph!*. You will see the graphical reprentation of the output.

## Input Variables

This will help not to hard code the values of ami, keys, etc. This is to paramaterize the configurations.

### Define variables

* Extract our region into a variable. so create another file `variables.tf` and add the following contents.

```
variable "region" {
  default = "ap-southeast-1"
}
```

* having the variable defined, now we have to use it in our example.tf file.

```
  provider "aws" {
    region  = var.region
  }
```
Note: if any variable is used in the configuration files, then these variables need to be declared in the *variables.tf* file, so that there is a default value assigned.

### Assigning variables

There are various ways to assign variables, following shows the order of precedence

#### Command line flags

we can set the variables at command line using the *-var* option

```
$ terraform apply -var 'region=us-east-1' -var 'ami=ami-2757f631'
```

if you want to destory the creation in the previous step try the following:

```
$ terraform destroy -var 'region=us-east-1' -var 'ami=ami-2757f631'
```

#### From File

we can persist the variables by creating a file and put all the variables into it. The name of the files could be `terraform.tfvars` or `*.auto.tfvars`. Terraform will be able to collect all the configured variables

example: > region = "us-east-2"

* if you have to give different names other than the default, then you can use the *-var-file* option to specify the file. you can use this multiple times to load multiple files.

```
$ terraform apply -var-file="secret-file.tfvars" -var-file="production.tfvars"
```

The contents in these files should be JSON

#### Environment Variables

here the variables are read as environment variables, the variables name need to be in the format `TF_VAR_name`. Example: `TF_VAR_region` var is to set the *region* variable. This style is suitable for populating *String* variables, not _LIST or MAP_ objects



## State of the Resources

If you want to know about the *state* of the configurations, then try the following command:

> $ terraform state list


