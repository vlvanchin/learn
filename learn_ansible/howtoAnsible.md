# Ansible:

- auto mation engine without agents
- uses SSH , very light weight

## install
> sudo apt install -y ansible

## install git
> sudo apt install -y git

## ansible configuration
> /etc/ansible/ansible.cfg

## ansible inventory
> /etc/ansible/hosts

## create ansible user in both controller and nodes
> sudo useradd -m ansible

## create password for the ansible user in all nodes (not controller)
sudo passwd ansible

## make sure to install and run openssh-server in the all the nodes
sudo apt install -y openssh-server
sudo service status ssh.service

## change to controller and create a key-pair for ansible user
ssh-keygen

## copy the public key from controller to each node
ssh-copy-id <node-ip>

## ssh to the node using the ansible user
ssh <node-ip>

in the nodes, ansible user is normal user ... now give ansible user with sudo privileges without passwords
```
ssh user@val8sgp6.mylabserver.com
user> sudo visudo
.. add the following
..
   ansible ALL=(ALL) NOPASSWD:ALL
..
  save file
```

### how to view all ansible docs
ansible-doc -l


### Adhoc command using ansible to run the setup module, change to ansible user and then run the following

$ ansible val8sgp -m setup | less
$ ansible val8sgp -m ping

- intalling python/3 and aptitude

$ ansible val8sgp6 -b -m apt -a "name=python-apt state=latest"
$ ansible val8sgp6 -b -m apt -a "name=python3-apt state=latest"
$ ansible val8sgp6 -b -m apt -a "name=aptitude state=latest"

- install httpd/apache2 in node (ubuntu)
$ ansible val8sgp6 -b -m apt -a "name=apache2 status=latest"

- to start the apache2 service
$ ansible val8sgp6 -b -m apt -a "name=apache2.service state=started"

- to stop the apache2 service
$ ansible val8sgp -b -m apt -a "name=apache2.service state=stopped"

- ansible playbook is like a bash script
$ ansible-playbook --help

### switch to ansible user for running the playbook

create a inventory
vim inv
- start content -
[webservers]
val8sgp6 ansible_host=val8sgp6.mylabserver.com
- end -


create the playbook "web.yml"
- start content -
```
--- # bootstrap webservers
- hosts: webservers
  become: yes

  tasks:
  - name: install apache2
    apt:
      name: apache2
      state: latest
  - name: create index.html file
    file:
      name: /var/www/html/index.html
      state: touch
  - name: add web content
    lineinfile:
      line: "here is (modified) some text"
      dest: /var/www/html/index.html
  - name: start apache2
    service:
      name: apache2
      state: started
```
- end -


execute the playbook (system is different based on versions of ansible and target OS)
$ ansible-playbook -i inv web.yml


### placing variables in command line
$ ansible-playbook -i inv web2.yml -e "target_service=apache2"

- begin - (web2.yml)
```
--- # bootstrap webservers
- hosts: webservers
  become: yes

  tasks:
  - name: install apache2
    apt:
      name: "{{ target_service }}"
      state: latest
  - name: create index.html file
    file:
      name: /var/www/html/index.html
      state: touch
  - name: add web content
    lineinfile:
      line: "here is (modified) some text"
      dest: /var/www/html/index.html
  - name: start apache2
    service:
      name: "{{ target_service }}"
      state: started
```
- end -

### Ansible facts about the nodes

$ ansible val8sgp6 -m setup

to get facts about ipv4
$ ansible val8sgp6 -m setup -a filter=*ipv4*

to get facts about hostname
$ ansible val8sgp6 -m setup -a filter=*hostname*

- output -
val8sgp6 | SUCCESS => {
    "ansible_facts": {
        "ansible_hostname": "val8sgp6"
    },
    "changed": false
}
- end -


use the varibles from ansible facts in the playbook (web3.yml)
- begin -
```
--- # bootstrap webservers
- hosts: webservers
  become: yes

  tasks:
  - name: install apache2
    apt:
      name: "{{ target_service }}"
      state: latest
  - name: create index.html file
    file:
      name: /var/www/html/index.html
      state: touch
  - name: add web content
    lineinfile:
      line: "{{ ansible_hostname }}"
      dest: /var/www/html/index.html
  - name: start apache2
    service:
      name: "{{ target_service }}"
      state: started
```
- end -

execute the playbook
$ ansible-playbook -i inv web3.yml -e "target_service=apache2"

not to collect facts, define property in playbook
```
  gather_facts: no
```

debug module for trouble shooting
update the playbook (web4.yml)
- begin -
```
--- # bootstrap webservers
- hosts: webservers
  become: yes
  gather_facts: yes
  tasks:
  - debug:
      var: target_service
  - name: install apache2
    apt:
      name: "{{ target_service }}"
      state: latest
  - name: create index.html file
    file:
      name: /var/www/html/index.html
      state: touch
  - name: add web content
    lineinfile:
      line: "{{ ansible_hostname }}"
      dest: /var/www/html/index.html
    register: task_debug
  - debug:
      msg: "Output of lineinfile is {{ task_debug }} "
  - name: start apache2
    service:
      name: "{{ target_service }}"
      state: started
```
- end -

execute
$ ansible-playbook -i inv web4.yml -e "target_service=apache2"

understanding ansible handlers

- begin (web5.yml) -

```
--- # bootstrap webservers
- hosts: webservers
  become: yes
  gather_facts: yes
  tasks:
  - debug:
      var: target_service
  - name: install apache2
    apt:
      name: "{{ target_service }}"
      state: latest
    notify:
      - "restart apache2"
  - name: create index.html file
    file:
      name: /var/www/html/index.html
      state: touch
  - name: add web content
    lineinfile:
      line: "{{ ansible_hostname }}"
      dest: /var/www/html/index.html
    notify:
      - "restart apache2"
    register: task_debug
  - debug:
      msg: "Output of lineinfile is {{ task_debug }} "
  handlers:
  - name: Attempt to restart apache2
    service:
      name: apache2
      state: restarted
```
- end -

execute the playbook
`$ ansible-playbook -i inv web5.yml -e "target_service=apache2"`

how to uninstall apache2 in val8sgp6

$ ansible val8sgp6 -b -m apt -a "name=apache2 state=absent"


