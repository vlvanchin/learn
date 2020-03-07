# Instructions
Your supervisor has asked you to help set up a new user for a very sensitive project. First, you'll need to create a new user account for pjohnson. Next, you'll need to create a project directory named /project51 at the root of the file system. Then, you'll need to create a file named TPSreport.txt in the /project51 directory, where pjohnson will keep his sensitive notes. Then, you'll create a FACL for the file /project51/TPSreport.txt so that only pjohnson has RWX access to this file. If you need to remove other file permissions to make this happen, please do so. Also, it is okay for root to have access to this file.

## Create user account `pjohnson` and the project directory and file.
Create the user account pjohnson with the following command:
 sudo useradd pjohnson  
Create the project directory with the following command:
 sudo mkdir /project51  
Create the TPSreport.txt file in the /project51 directory with the following command:
 sudo touch /project51/TPSreport.txt 

## Create FACL to permit user `pjohnson` access to file `TPSreprot.txt`.
Create a FACL for /project51/TPSreport.txt permitting user pjohnson RWX access:

 sudo setfacl -m u:pjohnson:rwx /project51/TPSreport.txt

## Remove access for other users from the file `/project51/TPSreport.txt`.
To remove other users' access, use the following command:

 sudo chmod o-rwx /project51/TPSreport.txt

