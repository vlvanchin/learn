# Instructions
You have just begun a new job as the operations lead at a small company. There is currently no formal server baseline, and it makes for a mixed configuration environment that is consuming more support and maintenance than it should. You have taken it upon yourself to create a baseline process using Ansible by creating a baseline role. You have noted the following commonalities that should be included in the baseline role:

Set /etc/motd based on a template.
Install the latest Nagios client.
Add the Nagios server to /etc/hosts.
Create a noc user.
Import the noc user's public key (copy authorized keys to /home/noc/.ssh/authorized_keys).
The role should be called "baseline" and should reside in /etc/ansible/roles on the ansible control node.

You will be testing your role on some newly requested webservers. A playbook called web.yml has been provided which deploys httpd to all servers in the web group (defined in your default inventory). You will need to edit the playbook to deploy the baseline role to the servers in the web group as well.

You will find the motd template, Nagios server IP information, the noc user's public key, and the web.yml playbook in /home/ansible/resources on the ansible control node.

Summary tasks list:

Create necessary directories and files for the baseline role.
Configure the role to deploy the /etc/motd template.
Configure the role to install the latest Nagios client.
Configure the role to add an entry to /etc/hosts for the Nagios server.
Configure the role to create the noc user and deploy the provided public key for the noc user on target systems (copy authorized_keys to /home/noc/.ssh/authorized_keys with the owner and group owner set as noc and the mode as 0600).
Edit web.yml to deploy the baseline role in addition to what it already does.
Verify you role works by deploying web.yml with Ansible.

Important notes:

For your convenience, Ansible is already on the control node.
The user ansible is on all servers with the appropriate shared keys for access to necessary servers from the control node.
The ansible user has sudo access with no password. It has the same password as cloud_user.
All necessary Ansible inventories have been created for you.


## Create a role called *baseline* in */etc/ansible/roles*.
Run the following commands to create the structure needed for the role:

sudo mkdir /etc/ansible/roles/baseline && sudo chown ansible.ansible /etc/ansible/roles/baseline
mkdir /etc/ansible/roles/baseline/{templates,tasks,files}
echo "---" > /etc/ansible/roles/baseline/tasks/main.yml

## Configure the role to deploy the */etc/motd* template.
cp /home/ansible/resources/motd.j2 /etc/ansible/roles/baseline/templates

Create a file called /etc/roles/baseline/tasks/deploy_motd.yml with the following content:

 ---
 - template:
     src: motd.j2
     dest: /etc/motd

Edit /etc/ansible/roles/baseline/tasks/main.yml to include the following lines at the bottom of the file

  - name: configure motd
    import_tasks: deploy_motd.yml


## Configure the role to install the latest Nagios client.
Create a file called /etc/ansible/roles/baseline/tasks/deploy_nagios.yml with the following content:

  ---
  - yum: name=nrpe state=latest

Edit /etc/ansible/roles/baseline/tasks/main.yml to include the following lines at the bottom of the file:

  - name: deploy nagios client
    import_tasks: deploy_nagios.yml

## Configure the role to add an entry to */etc/hosts* for the Nagios server.
Create a file called /etc/ansible/roles/baseline/tasks/edit_hosts.yml with the following content substituting <PROVIDED> with the IP specified in /home/ansible/resources/nagios_info.txt:

---
- lineinfile:
    line: "<PROVIDED> nagios.example.com"
    path: /etc/hosts

Edit /etc/ansible/roles/baseline/tasks/main.yml to include the following lines at the bottom of the file.

  - name: edit hosts file
    import_tasks: edit_hosts.yml

## Configure the role to create the *noc* user and deploy the provided public key for the *noc* user on target systems (copy *authorized_keys* to */home/noc/.ssh/authorized_keys* with the *owner* and *group owner* set as *noc* and the mode as *0600*).
Copy the file /home/ansible/resources/authorized_keys to /etc/ansible/roles/baseline/files/

Create a file called /etc/ansible/roles/baseline/tasks/deploy_noc_user.yml with the following content:

---
  - user: name=noc
  - file:
      state: directory
      path: /home/noc/.ssh
      mode: 0600
      owner: noc
      group: noc
  - copy:
      src: authorized_keys
      dest: /home/noc/.ssh/authorized_keys
      mode: 0600
      owner: noc
      group: noc

Edit /etc/ansible/roles/baseline/tasks/main.yml to include the following lines at the bottom of the file:

  - name: set up noc user and key
    import_tasks: deploy_noc_user.yml

## Edit *web.yml* to deploy the *baseline* role in addition to what it already does.
Edit /home/ansible/resources/web.yml to look like the following:

---
- hosts: webservers
  become: yes
  roles:
    - baseline
  tasks:
    - name: install httpd
      yum: name=httpd state=latest
    - name: start and enable httpd
      service: name=httpd state=started enabled=yes

## Run your playbook using the default inventory to verify your work.
Run ansible-playbook /home/ansible/resources/web.yml


### how to verify that it workds

- login to node1 and check for noc user 

$ ssh node1

-- check for noc user

$ id noc

>> output: uid=1004(noc) gid=1004(noc) groups=1004(noc)

- check for nrpe module exists

$ sudo yum list nrpe

Loaded plugins: amazon-id, rhui-lb, search-disabled-repos
Installed Packages
nrpe.x86_64                                                                  3.2.1-8.el7                                                                  @epel
[ansible@node1 ~]$





