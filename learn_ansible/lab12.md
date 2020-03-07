# Instructions
The development team has released the latest build of the enigma software. You have been approached to install the latest version on the QA systems. The development team has provided a task list of how to install the build. Rather than run through the steps on all the servers in your environment, you can use Ansible to run each task on all the servers at one time!

The Ansible control node has been configured for you and each QA server has already been configured for use with Ansible. The default inventory has been configured to include a qa-servers host group which includes the host that requires the new software. Execute the task list provided below using the appropriate Ansible ad-hoc commands.

Installation task list:

Download http://software.xyzcorp.com/enigma.tgz to /tmp on each host in qa-servers and verify the sha256 checksum via http://software.xyzcorp.com/enigma-checksum.txt. (Note: There is a bug in ansible get_url that currently only allows using the literal checksum value instead of a file location for the checksum argument! Thus, you must copy the checksum value from the provided checksum file and then paste that value into the ansible command rather than simply providing the checksum file URL in the command.)
Extract /tmp/enigma.tgz to /opt/ on all hosts in qa-servers.
Update the line of text "DEPLOY_CODE" in /opt/enigma/details.txt to the "CODE_RED" on each server in qa-servers.
Set the group ownership of the directory /opt/enigma/secret/ and each file contained within the directory so that the group owner is protected for each host in qa-servers.
Delete the file /opt/enigma/tmp/deployment-passwords.txt from all servers.


## Download http://software.xyzcorp.com/enigma.tgz to `/tmp` on each host in qa-servers and verify the sha256 checksum via http://software.xyzcorp.com/enigma-checksum.txt.
Run the following commands:

CHECKSUM=$(curl http://software.xyzcorp.com/enigma-checksum.txt | cut -f1 -d' ')
ansible qa-servers -m get_url -a "url=http://software.xyzcorp.com/enigma.tgz dest=/tmp/enigma.tgz checksum=sha256:$CHECKSUM"

## Extract `/tmp/enigma.tgz` to `/opt/` on all hosts in qa-servers.
Run ansible qa-servers -b -m unarchive -a "src=/tmp/enigma.tgz dest=/opt/ remote_src=yes"

## Update the line of text "DEPLOY_CODE" in `/opt/enigma/details.txt` to the "CODE_RED" on each server in qa-servers.
Run ansible qa-servers -b -m lineinfile -a "regexp=DEPLOY_CODE line=CODE_RED path=/opt/enigma/details.txt"

## Set the group ownership of the directory `/opt/enigma/secret/` and each file contained within the directory so that the group owner is `protected` for each host in qa-servers.
Run ansible qa-servers -b -m file -a "recurse=yes state=directory path=/opt/enigma/secret group=protected"

## Delete the file `/opt/enigma/tmp/deployment-passwords.txt` from all servers.
Run ansible all -b -m file -a "state=absent path=/opt/enigma/tmp/deployment-passwords.txt"

