# inital setup in control node for the ansible use

```
[ansible@control ~]$ ls
confidential  htpasswd.j2  vhost.conf.j2
[ansible@control ~]$ cat confidential 
htpasswd: .Kizb.dpR44mEP5eIv6X90n1
[ansible@control ~]$ cat htpasswd.j2 
agent:{{ htpasswd }}
[ansible@control ~]$ cat vhost.conf.j2 
<VirtualHost {{ ansible_default_ipv4.address }}:80>
ServerAdmin webmaster@xyzcorp.com
DocumentRoot /var/www/html

<Directory "/var/www/html"> 
  AuthType Basic
  Authname "Authorized Users Only"
  AuthUserFile /etc/httpd/conf/htpasswd
  Require valid-user
</Directory>
</VirtualHost>
[ansible@control ~]$ 
```

## instructions

You must create a modular playbook used for webserver management. Create a playbook called /home/ansible/webserver.yml that meets the following requirements:

On the host group webservers:

Deploy httpd.
You can assume all necessary firewall rules have been deployed.
Start and enable httpd.
Configure virtual host using the provided templates in /home/ansible/vhost.conf.j2 and /home/ansible/htpasswd.j2.
Note: The template references a variable defined in /home/ansible/confidential which must be included as a variable file in your playbook.
Use ansible vault to secure /home/ansible/confidential with password "I love ansible".
Run the data job stored in /opt/data-job.sh on each node in webservers asynchronously, without polling for status.
Create tags for the following tasks:
base-intall for httpd installation and service configuration.
vhost for virtual host deployment.
data-job to execute the asynchronous data job.
The Ansible control node has been configured for you and each webserver has already been configured for use with Ansible. The default inventory has been configured to include the webservers group and sample hosts.

### Use ansible-vault to protect the confidential information.

Use ansible-vault to encrypt /home/ansible/confidential to protect the confidential information stored within using the password "I love ansible".

Run ansible-vault encrypt /home/ansible/confidential and supply the password "I love ansible".

### Create a playbook that deploys httpd on webservers.
Create a playbook in /home/ansible/webserver.yml that deploys httpd on webservers. It should be tagged with base-install and contain a handler that restarts the httpd daemon that is flagged by both installation and service manipulation for httpd.

Create the file /home/ansible/webserver.yml and add the following content:

- hosts: webservers
  become: yes
  vars_files:
    - /home/ansible/confidential
  tasks:
    - name: install httpd
      yum:
        name: httpd
        state: latest
      notify: httpd service
      tags:
        - base-install
  handlers:
    - name: Restart and enable httpd
      service:
        name: httpd
        state: restarted
        enabled: yes
      listen: httpd service


### Deploy the templates stored on the control node to the webservers group.
Configure /home/ansible/webserver.yml to deploy the templates /home/ansible/vhost.conf.j2 and /home/ansible/htpasswd.j2 stored on the control node to the webservers group. httpd must restart on config change. The tasks should be tagged vhost.

Add the following text to /home/ansible/webserver.yml just before the handler section:

    - name: configure virtual host
      template:
        src: /home/ansible/vhost.conf.j2
        dest: /etc/httpd/conf.d/vhost.conf
      notify: httpd service
      tags:
        - vhost
    - name: configure site auth
      template:
        src: /home/ansible/htpasswd.j2
        dest: /etc/httpd/conf/htpasswd
      notify: httpd service
      tags:
        - vhost

### Asynchronously execute data-job on webservers.
Configure /home/ansible/webserver.yml to asynchronously execute /opt/data-job.sh located on webservers with a timeout of 600 seconds and no polling. The task should be tagged with data-job.

Add the following text to /home/ansible/webserver.yml just before the handler section:

    - name: run data job
      command: /opt/data-job.sh
      async: 600
      poll: 0
      tags:
        - data-job


### Execute playbook to verify your playbook works correctly.
Execute playbook /home/ansible/webserver.yml to verify your playbook works correctly.

Run ansible-playbook --ask-vault-pass /home/ansible/webserver.yml from the control node providing the vault password "I love ansible".


### how to verify the above playbook tasks

#### check to access node1 

$ curl node1

-output- you will receive unauthorized as you need to give the password
<!DOCTYPE HTML PUBLIC "-//IETF//DTD HTML 2.0//EN">
<html><head>
<title>401 Unauthorized</title>
</head><body>
<h1>Unauthorized</h1>
<p>This server could not verify that you
are authorized to access the document
requested.  Either you supplied the wrong
credentials (e.g., bad password), or your
browser doesn't understand how to supply
the credentials required.</p>
</body></html>
-end-

#### ssh to node1 and see if the process data-job is running

$ ssh node1

node1> ps -fe | grep data

root      1689     2  0 Apr20 ?        00:00:00 [xfs-data/xvda1]
root     15257 15256  0 00:46 ?        00:00:00 /bin/sh /opt/data-job.sh
ansible  15284 15264  0 00:47 pts/0    00:00:00 grep --color=auto data

#### in control node, just run the base install portion need to provide password for below tasks

$ ansible-playbook webserver.yml --ask-vault-pass --tags base-install


#### run the vhost section

$ ansible-playbook webserver.yml --ask-vault-pass --tags vhost

#### run the data-job

$ ansible-playbook webserver.yml --ask-vault-pass --tags data-job


