Create the user accounts noted in /home/ansible/userlist.txt.
Copy the authorized_keys file for each user to the correct location so the new accounts can log in with ssh key authentication.
Ensure auditd is enabled and running on all systems.


$ cat  /home/ansible/userlist.txt
# Please create the the below users on all hosts in dbsystems
# You will find a pregenerated key pair and an authorized key file for each user in /home/ansible/keys

# User accounts to create:
consultant
supervisor

Create the user accounts noted in `/home/ansible/userlist.txt`

- to create the consultant i both the nodes

$ ansible dbsystems -b -m user -a "name=consultant"

- to create the supervisor in both the nodes

$ ansible dbsystems -b -m user -a "name=supervisor" 

## Place key files in the correct location, `/home/$USER/.ssh/authorized_keys`, on hosts in `dbsystems`

- update .ssh folders on the dbsystems with the repective owner/group and permissions 755 

$ ansible dbsystems -b -m file -a "path=/home/consultant/.ssh state=directory owner=consultant group=consultant mode=0755"

$ ansible dbsystems -b -m file -a "path=/home/supervisor/.ssh state=directory owner=supervisor group=supervisor mode=0755"

- now copy the respective authorizied keys to all the nodes for the respective users

$ ansible dbsystems -b -m copy -a "src=/home/ansible/keys/consultant/authorized_keys dest=/home/consultant/.ssh/authorized_keys mode=0600 owner=consultant group=consultant"

$ ansible dbsystems -b -m copy -a "src=/home/ansible/keys/supervisor/authorized_keys dest=/home/supervisor/.ssh/authorized_keys mode=0600 owner=supervisor group=supervisor"

- Ensure `auditd` is enabled and running on all hosts. This includes both nodes and the control node

$ ansible all -b -m service -a "name=auditd state=started enabled=yes"




