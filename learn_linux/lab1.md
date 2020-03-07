# Instructions
We have some users that need to be set up on a new machine.

Tony Stark, Diana Prince, and Carol Danvers are developers for a project we're involved with. We need to add user accounts for them to the server, then create the superhero group for all of them to be members of.

Tony Stark is a superuser, so we'll replace his primary group with the wheel group.

But wait! We realize a couple weeks in that Diana Prince has been forced to take leave, in order to fight evil. We'll have to lock her account until she can return.


## Add the Users to the Server
To add users to the system we can run

useradd <username>

So we would run

useradd tstark

useradd cdanvers

useradd dprince
## Create the `superhero` Groupg
To create a new group we would run

groupadd <groupname>

So for this task

groupadd superhero

## Set `wheel` Group as the the `tstark` Account's Primary Group
For this task we would run usermod like this

usermod -g wheel tstark

## Add `superhero` as a Supplementary Group on All Three Users
There isn't an easy way to do this all at once, so we need to run the following command for each user

usermod -aG superhero <username>

So

usermod -aG superhero tstark

usermod -aG superhero dprince

usermod -aG superhero cdanvers

## Lock the `dprince` Account
To lock an account all we have to do is run:

usermod -L dprince

## to find the details of the user group and secondary group

try to use id <username>

$ id <username>

## to check about the password age and activity of the password

try the chage command

$ chage -l <username>

## how to check if the user is locked, use the status option (S)

try the passwd command

$ sudo passwd -S <username>



