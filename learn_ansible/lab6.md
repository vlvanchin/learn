# instructions
You are to set up an automation to pull down a data file from a notoriously unreliable third-party system for integration purposes. Create a playbook that attempts to pull down http://apps.l33t.com/transaction_list to the localhost. The playbook should gracefully handle the site being down by outputting the message "l33t.com appears to be down. Try again later." to stdout. If the task succeeds, the playbook should write "File downloaded." to stdout. No matter if the playbook errors or not, it should always output "Attempt completed." to stdout.

If the report is collected, your playbook should write and edit the file to replace all occurrences of #BLANKLINE with a line break \n.

Summary tasks list:

## Create a playbook called /home/ansible/report.yml.

- initialize the report.yml playbook

$ echo "---" >> /home/ansible/report.yml


## Configure the playbook to download http://apps.l33t.com/transaction_list to /home/ansible/transaction_list on the localhost and output "File downloaded." to stdout.

- update the report.yml file

```
- hosts: localhost
  tasks:
    - name: download tranaction_list
      get_url: url=http://apps.l33t.com/transaction_list dest=/home/ansible/transaction_list
    - debug: msg="File downloaded"
```


## Configure the playbook to handle connection failure by outputting "l33t.com appears to be down. Try again later." to stdout.

- update the report.yml file to handly failure using similar to try-catch, which is *block* and *rescue*

```
- hosts: localhost
  tasks:
  - name: download transction_list
    block:
     - get_url: url=http://apps.l33t.com/transaction_list dest=/home/ansible/transaction_list
     - debug: msg="File downloaded"
    rescue:
     - debug: msg="l33t.com appears to be down.  Try again later."
```

## Configure the playbook to output "Attempt Completed" to stdout no matter if it was successful or not.

- update the report.yml file and add the **always** that is similar to **finally**

```
- hosts: localhost
  tasks:
  - name: download transction_list
     block:
      - get_url: url=http://apps.l33t.com/transaction_list dest=/home/ansible/transaction_list
      - debug: msg="File downloaded"
    rescue:
      - debug: msg="l33t.com appears to be down.  Try again later."
    always:
      - debug: msg="Attempt completed."
```

## Configure the playbook to replace all instances of #BLANKLINE with the line break character \n.

- update the report.yml and in the *block* section add the replace task

```
- hosts: localhost
  tasks:
  - name: download transction_list
     block:
      - get_url: url=http://apps.l33t.com/transaction_list dest=/home/ansible/transaction_list
      - replace: path=/home/ansible/transaction_list regexp=#BLANKLINE replace='\n'
      - debug: msg="File downloaded"
    rescue:
     - debug: msg="l33t.com appears to be down.  Try again later."
    always:
     - debug: msg="Attempt completed."
```

## Run your playbook using the default inventory to verify your work

- execute the following using the default inventory.

```
$ ansible-playbook /home/ansible/report.yml
```



