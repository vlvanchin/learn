# Instructions
Your company has been increasing the deployment of small broacher-style websites for clients. The head of IT has decided that each client should have their own web servers for better client isolation and has tasked you with creating concept automation to quickly deploy web-nodes with simple static website content.

You have been provided an ansible control node and 2 test lab servers (node1 and node2) that have been preconfigured with the ansible user and key.

You must create an ansible inventory in /home/ansible/inventory containing a host group named web. The web group should contain node1 and node2.

Furthermore, you must design an Ansible playbook that will execute the following tasks on your configured inventory: install httpd, start and enable the httpd service, and install a simple website provided on a repo server. Create the playbook in /home/ansible/web.yml. 


The simple website may be accessed from http://repo.example.com/website.tgz.

Summary tasks list:

Create an inventory in /home/ansible/inventory containing a host group named web. The web group should contain node1 and node2.
Create a playbook in /home/ansible/web.yml.
Configure the playbook to install httpd on the web group.
Configure the playbook to start and enable the httpd service on the web group.
Configure the playbook to retrieve the website from http://repo.example.com/website.tgz on each server in the web group.
Configure the playbook to unarchive the website into /var/www/html on all servers in the web group.
Execute the playbook you created using the inventory you created to verify your work.

## Create an inventory in */home/ansible/inventory* containing a host group named *web*. The *web* group should contain node1 and node2.

- switch user as ansible

$ sudo su - ansible

- create inventory file and enter the following 

$ vim inventory

-content-
[web]
node1
node2
-end-


## Create a playbook in */home/ansible/web.yml*.

- create web.yml

$ vim web.yml

-content-
---
-end-

## Configure the playbook to install *httpd* on the *web* group

- update the web.yml with the following contents to install httpd

$ vim web.yml

```
- hosts: web
  become: yes
  tasks:
    - name: install httpd
      yum: name=httpd state=latest
```

## Configure the playbook to start and enable the *httpd* service on the *web* group

- update the web.yml with the following to configure service

```
    - name: start and enable httpd
      service: name=httpd state=started enabled=yes
```

## Configure the playbook to retrieve the website from *http://repo.example.com/website.tgz* on each server in the web group

- update the web.yml with the following to pull content and upload to each nodes' tmp folder

```
    - name: retrieve website from repo
      get_url: url=http://repo.example.com/website.tgz dest=/tmp/website.tgz
```

## Configure the playbook to unarchive the website into */var/www/html* on all servers in the web group.

- update the web.yml with the following for unarchive the tgz file to /var/www/html folder

```
    - name: install website contents
      unarchive: remote_src=yes src=/tmp/website.tgz dest=/var/www/html/
```

## Execute the playbook you created using the inventory you created to verify your work.

- use the following command to execute the playbook from control node

$ ansible-playbook -i inventory web.yml

## Test that our web site is working

$ curl node1/home.html

