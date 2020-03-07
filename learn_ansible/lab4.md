- Create the inventory file in /home/ansible/inventory.
- Configure the host group media to contain media1 and media2.
- Define the following variables for media with their accompanying values:
-- media_content should be set to /var/media/content/.
-- media_index should be set to /opt/media/mediaIndex.
- Configure the host group webservers to contain the hosts web1 and web2.
- Define the following variables for webservers with their accompanying values:
-- httpd_webroot should be set to /var/www/.
-- httpd_config should be set to /etc/httpd/.
- Define the variable script_files for specifically for web1. The value of script_files should be set to /usr/local/scripts.
- You can run /home/ansible/scripts/backup.sh to test your inventory. If you have correctly configured the inventory, it should not error.
- Do not edit anything in /home/ansible/scripts/.

## Create the inventory file in */home/ansible/inventory*

$ sudo touch /home/ansible/inventory

## Configure the host group *media* to contain media1 and media2

### using vim add the following to /home/ansible/inventory

[media]
media1
media2

### using echo command add the following host group *media*

echo "[media]" >> /home/ansible/inventory
echo "media1" >> /home/ansible/inventory
echo "media2" >> /home/ansible/inventory

## Define variables for *media* with their accompanying values.

### using vim add the following to /home/ansible/group_vars/media file

media_content: /var/media/content/
media_index: /opt/media/mediaIndex

### using echo commands add the variables to /home/ansible/group_vars/media file

mkdir /home/ansible/group_vars
touch /home/ansible/group_vars/media
echo "media_content: /var/media/content/" >> /home/ansible/group_vars/media
echo "media_index: /opt/media/mediaIndex" >> /home/ansible/group_vars/media

## Configure the host group *webservers* to contain the hosts web1 and web2

### update the /home/ansible/inventory file with the following contents
[webservers]
web1
web2

### this can be accomplished using echo command

echo "[webservers]" >> /home/ansible/inventory
echo "web1" >> /home/ansible/inventory
echo "web2" >> /home/ansible/inventory

## Define variables for *webservers* with their accompanying values

### create the new file webservers in group_vars folder

touch /home/ansible/group_vars/webservers

### use echo to set the variables httpd_webroot and httpd_config 

echo "httpd_webroot: /var/www/" >> /home/ansible/group_vars/webservers
echo "httpd_config: /etc/httpd/" >> /home/ansible/group_vars/webservers

### use the vim to add the following 

httpd_webroot: /var/www/
httpd_config: /etc/httpd/

## Define the variable *script_files* specifically for web1. The value of *script_files* should be set to '/usr/local/scripts'

mkdir /home/ansible/host_vars
touch /home/ansible/host_vars/web1
echo "script_files: /usr/local/scripts" >> /home/ansible/host_vars/web1

## You can run /home/ansible/scripts/backup.sh to test your inventory. If you have correctly configured the inventory, it should not error.

/home/ansible/scripts/backup.sh

#### sh script

[ansible@control1 scripts]$ cat backup.sh 
#!/bin/sh

# Need to take a tag varialbe
# mediavars to test media
# webservervars to test webservers
# hostvar to test host var
# use playbook tags maybe?

case "$1" in
  mediavars)
    ansible-playbook -i /home/ansible/inventory /home/ansible/scripts/backup.yml --tags "mediavars"
    exit $?
    ;;
  webservervars)
    ansible-playbook -i /home/ansible/inventory /home/ansible/scripts/backup.yml --tags "webservervars"
    exit $?
    ;;
  hostvar)
    ansible-playbook -i /home/ansible/inventory /home/ansible/scripts/backup.yml --tags "hostvar"
    exit $?
    ;;
  *)
    ansible-playbook -i /home/ansible/inventory /home/ansible/scripts/backup.yml
    exit $?
esac

#### yml file
[ansible@control1 scripts]$ cat backup.yml                                          [0/308]
---
- hosts: all
  become: yes
  tasks:
    - name: create backup directories
      file:
        path: /mnt/backup_vol/{{ ansible_hostname }}
        state: directory
      tags:
        - hostvar
        - webservervars
        - mediavars
- hosts: web1
  become: yes
  tasks:
    - name: backup scripts
      archive:
        dest: /mnt/backup_vol/{{ ansible_hostname }}/scripts.tgz
        path: "{{ script_files }}"
      tags: hostvar

- hosts: webservers
  become: yes
  tasks:
    - name: backup httpd configs
      archive:
        dest: /mnt/backup_vol/{{ ansible_hostname }}/httpd_configs.tgz
        path: "{{ httpd_config }}"
      tags: webservervars
    - name: backup webroot
      archive:
        dest: /mnt/backup_vol/{{ ansible_hostname }}/httpd_webroot.tgz
        path: "{{ httpd_webroot }}"
      tags: webservervars

- hosts: media
  become: yes
  tasks:
    - name: backup media content
      archive:
        dest: /mnt/backup_vol/{{ ansible_hostname }}/media_content.tgz
        path: "{{ media_content }}"
      tags: mediavars
    - name: backup media index
      archive:
        dest: /mnt/backup_vol/{{ ansible_hostname }}/media_index.tgz
        path: "{{ media_index }}"
      tags: mediavars

#### inventory 

[ansible@control1 ~]$ cat inventory 
[media]
media1
media2

[webservers]
web1
web2

#### group_vars

[ansible@control1 ~]$ cd  group_vars/
[ansible@control1 group_vars]$ ls
media  webservers
[ansible@control1 group_vars]$ cat media 
media_content: /var/media/content/
media_index: /opt/media/mediaIndex

[ansible@control1 group_vars]$ cat webservers 
httpd_webroot: /var/www/
httpd_config: /etc/httpd/

#### host_vars

[ansible@control1 group_vars]$ cd ../host_vars/
[ansible@control1 host_vars]$ ls
web1
[ansible@control1 host_vars]$ cat web1 
script_files: /usr/local/scripts
[ansible@control1 host_vars]$



