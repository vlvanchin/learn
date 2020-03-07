# Aws Tips

The following commands help to know about AWS cli tool

## to get instanceId based on the instance-type

```
aws ec2 describe-instances --filters "Name=instance-type,Values=t2.micro" --profile vanchin-vorto | grep InstanceId
```

## to get list of ec2 instances base on tag-key with 'Owner' value

```
aws ec2 describe-instances --filters "Name=tag-key,Values=Owner"

```

## to create a bucket based on region and create bucket configuration

```
aws s3api create-bucket --bucket vanchin-my-bucket-2 --region ap-southeast-1 --create-bucket-configuration LocationConstraint=ap-southeast-1
```

## to copy a py file from local to s3 bucket ...few examples

```
aws s3 cp get-pip.py s3://vanchin-my-bucket-2.s3.amazonaws.com/get-pip.py
aws s3 cp get-pip.py s3://vanchin-my-bucket-2
aws s3 cp awscli-bundle.zip s3://vanchin-my-bucket-2
aws s3 cp ~/Documents/git/vorto/repository/repository-server/target/infomodelrepository.jar s3://vanchin-my-bucket-2
```

## to list all the contents in s3 bucket

```
aws s3 ls
aws s3 ls s3://vanchin-my-bucket-2
```

## to remove contents from s3 bucket examples

```
- aws s3 rm s3://vanchin-my-bucket-2/infomodelrepository.jar
- aws s3 rm s3://vanchin-my-bucket-2/
```
