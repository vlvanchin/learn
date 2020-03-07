A colleague of yours was the unfortunate victim of a scam email, and their network account was compromised. Shortly after you finished helping them pack up their desk, your boss gave you the assignment to promote system security through deploying a hardened sudoers file. You will need to create an Ansible template of the sudoers file that meets the following criteria:

A file named /etc/sudoers.d/hardened to deploy on all ansible inventory servers. WARNING: Do NOT edit the default sudoers file, doing so may break your exercise environment. Additionally, always validate any file placed in /etc/sudoers.d with /sbin/visudo -cf <filename> prior to deployment!!

The file must be validated using /sbin/visudo -cf before deployment.

You will need to create an accompanying playbook in /home/ansible/security.yml that will deploy this template to all servers in the default inventory.


Summary tasks list:

Create a template sudoers file in */home/ansible/hardened.j2 *that produces a file with appropriate output for each host.
The deployed file should resemble the following, except with the IP and hostnames customized appropriately:
  %sysops 34.124.22.55 = (ALL) ALL
  Host_Alias WEBSERVERS = server1, server2
  Host_Alias DBSERVERS = serverA, serverB
  %httpd WEBSERVERS = /bin/su - webuser
  %dba DBSERVERS = /bin/su - dbuser
Create a playbook in /home/ansible/security.yml that uses the template module to deploy the template on all servers in the default ansible inventory after validating the syntax of the generated file.
Note: You may find it easier to have the play output to /home/ansible/test and validate manually using /sbin/visudo -cf <filename> before using the template module's validate.
IMPORTANT: Do not deploy any file to /etc/sudoers.d/ without first validating with visudo! A syntax error in a sudoers file will break sudo on the system and require starting the exercise over again!
Run the playbook and ensure the files deployed correctly.

Important notes:

For your convenience, Ansible has been installed on the control node.
The user ansible has been already created on all servers with the appropriate shared keys for access to the necessary servers from the control node. It has the same password as cloud_user.
All necessary Ansible inventories have been created for you.

## Create a template *sudoers* file in */home/ansible/hardened.j2* that produces a file with appropriate output for each host.

- try the following 

$ vim /home/ansible/hardened.j2

## The deployed file should resemble the the example file except with the *IP* and *hostnames* customized appropriately.

- add the following to 
-- Grant users in the sysops group the ability to run all commands as root for each local system by IP address. This would be what the entry in your result file except with the target system's IP: %sysops 34.124.22.55 = (ALL) ALL.
-- Define the host_alias group WEBSERVERS to contain all servers in the ansible web inventory group: Host_Alias WEBSERVERS = <host name>
-- Define the host_alias group DBSERVERS to contain all servers in the ansible database inventory group: Host_Alias DBSERVERS = <host name>
-- Grant users in the httpd group the ability to sudo su - webuser on the WEBSERVERS hosts: %httpd WEBSERVERS = /bin/su - webuser
-- Grant users in the dba group sudo su - dbuser on the DBSERVERS hosts: %dba DBSERVERS = /bin/su - dbuser

```
    %sysops {{ ansible_default_ipv4.address }} = (ALL) ALL
    Host_Alias WEBSERVERS = {{ groups['web']|join(' ') }}
    Host_Alias DBSERVERS = {{ groups['database']|join(' ') }} 
    %httpd WEBSERVERS = /bin/su - webuser
    %dba DBSERVERS = /bin/su - dbuser
```

## Create a playbook in */home/ansible/security.yml* that uses the template module to deploy the template on all servers in the default Ansible inventory after validating the syntax of the generated file.

- create the security.yml file with the following with root permission

```
- hosts: all
  become: yes
  tasks:
  - name: deploy sudo template
    template:
      src: /home/ansible/hardened.j2
      dest: /etc/sudoers.d/hardened
      validate: /sbin/visudo -cf %s
```

## Run the playbook and ensure the files are correctly deployed.

- use the default inventory

$ ansible-playbook /home/ansible/security.yml 

- the default inventory in /etc/ansible/hosts contains the following

```
localhost
[nodes]
node1
node2
[web]
node1
[database]
node2

```
