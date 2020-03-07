# Instructions
The team has been working on automating the builds for common services using Ansible. You have been tasked with creating the build for a common NFS server. Write a playbook that satisfies the following requirements:

On nfs:

Make sure nfs-utils is installed.

Configure /etc/exports via an Ansible template stored in /home/ansible/exports.j2. Deploy the template so that /mnt/nfsroot is exported with read and write to all hosts.

Note: Your template file should contain the following with the variable {{ share_path }} being defined within your playbook:
```
{{ share_path }} *(rw)
```
Note: The file /etc/exports on nfs should have the following content once deployed:

/mnt/nfsroot *(rw)
Create a handler that runs the command exportfs -a if the file /etc/exports is modified in a playbook task.

You can assume all necessary firewall rules have been deployed.

On remote:

Configure /etc/hosts from a template file stored on control at /home/ansible/etc.hosts.j2 with the following entries:
```
  127.0.0.1 localhost, {{ ansible_hostname }}
  {{ nfs_ip }}  {{ nfs_hostname }}
```
Note: You should populate the variables {{ nfs_ip }} and {{ nfs_hostname }} using magic variables in your playbook.
Create users from file stored on control at /home/ansible/user-list.txt only if the remote host has the file /opt/user-agreement.txt.

The Ansible control node has been configured for you and all other servers have already been configured for use with Ansible. The default inventory has been configured to include the group remote and server nfs.

## Create the necessary template files on the Ansible control node.

Create the files exports.j2 and etc.hosts.j2 in /home/ansible with the noted content:

/home/ansible/exports.j2
```
{{ share_path }} *(rw)
```

/home/ansible/etc.hosts.j2
```
127.0.0.1    localhost {{ ansible_hostname}}
{{ nfs_ip }}    {{ nfs_hostname }}
```

## Create a playbook for the server 'nfs' in the Ansible inventory.

- create a playbook as nfs.yml

```
- hosts: nfs
  become: yes
  vars:
    share_path: /mnt/nfsroot
  tasks:
    - name: install nfs
      yum:
        name: nfs-utils
        state: latest
    - name: start and enable nfs-server
      service:
        name: nfs-server
        state: started
        enabled: yes
    - name: configure exports
      template:
        src: /home/ansible/exports.j2
        dest: /etc/exports
      notify: update nfs
  handlers:
    - name: update nfs exports
      command: exportfs -a
      listen: update nfs
```

## Add a play for the remote host group.

- update the nfs.yml for adding another play *remote*

```
  - hosts: remote
    become: yes
    vars:
      nfs_ip: "{{ hostvars['nfs']['ansible_default_ipv4']['address'] }}"
      nfs_hostname: "{{ hostvars['nfs']['ansible_hostname'] }}"
    vars_files:
      - /home/ansible/user-list.txt
    tasks:
      - name: configure hostsfile
        template:
          src: /home/ansible/etc.hosts.j2
          dest: /etc/hosts.nfslab
      - name: get file status
        stat:
          path: /opt/user-agreement.txt
        register: filestat
      - name: debug info
        debug:
          var: filestat
      - name: create users
        user:
          name: "{{ item }}"
        when:  filestat.stat.exists
        loop: "{{ users }}"
```

## Execute playbook to verify your playbook works correctly.

$ ansible-playbook nfs.yml

how to check and verify

$ ssh nfs

- inside nfs server 

$ cat /etc/exports

output : 

/mnt/nfsroot *(rw)

- check if nfs-server is running

$ systemctl status nfs-server

output: nfs-server should be running.

$ ssh node2

- check if the user van is created

$ id van



