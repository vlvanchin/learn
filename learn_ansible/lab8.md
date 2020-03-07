# Instructions
Your supervisor has asked you to find a way to automate and audit basic system configuration for new servers in your environment. Given that Ansible is already configured at a basic level in your environment, the simplest solution would be to just write a playbook for bootstrapping your new hosts. Create a playbook called /home/ansible/bootstrap.yml to fulfill the following boot strap requirements:

All servers:

- Edit /etc/hosts to include the following entry:
-- ansible.xyzcorp.com  169.168.0.1
- Install elinks
- Create the user xyzcorp_audit
- Copy the files /home/ansible/motd and /home/ansible/issue to /etc/

Network servers:

- Install nmap-ncat
- Create the user xyzcorp_network

SysAdmin servers:

- Copy /home/ansible/scripts.tgz from the control node to /mnt/storage


The Ansible control node has been configured for you and each testing server has already been configured for use with Ansible. The default inventory has been configured to include a the groups network and sysadmin. Each group includes a sample host.

## Create a basic playbook for all servers in the Ansible inventory.

```
- hosts: all
  become: yes
  tasks:
    - name: edit host file
      lineinfile:
        path: /etc/hosts
        line: "ansible.xyzcorp.com 169.168.0.1"
    - name: install elinks
      package:
        name: elinks
        state: latest
    - name: create audit user
      user:
        name: xyzcorp_audit
        state: present
    - name: update motd
      copy:
        src: /home/ansible/motd
        dest: /etc/motd
    - name: update issue
      copy:
        src: /home/ansible/issue
        dest: /etc/issue
```

## Add a section to the playbook for the Network servers in the Ansible inventory

```
 - hosts: network
   become: yes
   tasks:
     - name: install netcat
       yum:
         name: nmap-ncat
         state: latest
     - name: create network user
       user:
         name: xyzcorp_network
         state: present
``` 


## Add a section to the playbook for the SysAdmin servers in the Ansible inventory.

```
 - hosts: sysadmin
   become: yes
   tasks:
     - name: copy tarball
       copy:
         src: /home/ansible/scripts.tgz
         dest: /mnt/storage/
```

## Execute playbook to verify your playbook works correctly

```
ansible-playbook bootstrap.yml
```
