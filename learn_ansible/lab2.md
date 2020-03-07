- execute the ping module in node2 

$ ansible -i /home/ansible/inventory node2 -m ping 


- execute the ping module in node1 and save the output to output file

$ ansible -i /home/ansible/inventory node1 -m ping > /home/ansible/output


