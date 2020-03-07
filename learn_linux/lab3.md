# Instructions
We need to set up a new server for a developer to use. He needs to be able to connect with ssh from server1 to server2 as the dev user, without having to enter a password password.

Once that's set up there are some tar files on server1 that need to be copied over and extracted. To enable the developer to verify that it was done correctly, we should have all the output from the extraction go to /home/dev/tar-output.log.

We need to make sure that new files have the correct permissions (readable and writeable by only the user) by setting the umask correctly.

Once complete we should verify permissions on the very important /home/dev/deploy.sh script and run it.

The password for the user dev is the same as for cloud_user.

## Enable SSH to Connect Without a Password from the `dev` User on `server1` to the `dev` User on `server2`
We need to use SSH keys to satisfy this requirement, so generate them with this:

` [cloud_user@host] sudo su - dev`

` [dev@host]$ ssh-keygen`

Then run:

` [dev@host]$ ssh-copy-id dev@server2 `

## Copy All tar Files rom `/home/dev/` on `server1` to `/home/dev/` on `server2`, and Extract Them Making Sure the Output is Redirected to `/home/dev/tar-output.log`
We need to use a method of copying files over a network. scp is the best tool, like this:

`[user@host]$ scp *.gz `

Then connect to server2 using ssh:

`[user@host]$ ssh `

Then we can extract the files:

```
[user@server2]$ tar -xvf deploy_content.tar.gz >> tar-output.log
[user@server2]$ tar -xvf deploy_script.tar.gz >> tar-output.log
```
Make sure to use `>>`, so that the output is appended rather than overwritten.

## Set the Umask So That New Files Are Only Readable and Writeable by the Owner
The task is asking to make new files with the following permission: `0600`

So we can do subtraction to figure out what our umask should be.

`0666 <-- Default`

`0600 <-- Desired`

----

`0066` <-- What we need to set

So we run `umask 0066`

## Verify the `/home/dev/deploy.sh` Script Is Executable and Run It
First we check permissions on deploy.sh:
```
 [user@host]$ ls -l deploy.sh
 -rw-r--r--. 1 dev dev 151 Mar 27 15:11 deploy.sh
```

There's no execute bit. Let's add one:
```
 [user@host]$ chmod +x deploy.sh
```
And then run it:
```
 [user@host]$ ./deploy.sh

```

