# Dockerized Jenkins

## Install and run Jenkins

- docker pull jenkins/jenkins:2.107.3
- docker run -d -p 8080:8080 -p 50000:50000 --name myjenkins jenkins/jenkins:2.107.3

This will have all the plugins and configurations stored in the jenkins home folder, when stopped all the data is deleted

## how to get admin password
- can be refered $JENKINS_HOME/secrets/initialAdminPassword 
- run the following

`$ docker exec jenkins-dev /bin/bash -c 'cat $JENKINS_HOME/secrets/initialAdminPassword'`

## To run Jenkins and keep data

* there are two ways to keep data

	- bind mounts
		to mount a directory to docker as volume_dir: 
		> How to know: if the option -v is used and '/' is used in the host value then we are using bind mount.
			
		this also gets into permissions errors and so...

		You must set the correct permissions in the host before you mount volumes sudo chown 1000 volume_dir; where 1000 is the userid of jenkins user
		```
		$ mkdir jenkins_home
		$ chown 1000 /home/van/jenkins_home
		$ docker run -d -p 8080:8080 -p 50000:50000 -v /home/van/jenkins_home:/var/jenkins_home --name myjenkins jenkins/jenkins:2.107.3
		```

	- create a docker volumn and use it
		```
		// use the following to create the volume
		$ docker volumn create jenkins-data
		
		// view the created volume
		$ docker volume ls
		
		// use the created volume 
		$ docker run -d -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home --name myjenkins jenkins/jenkins:2.107.3

				
## How to upgrade Jenkins to a new version
* here we can do that by specifying the tag of the jenkins release as below

	> $ docker run -d -p 8080:8080 -p 50000:50000 -v /home/van/jenkins_home:/var/jenkins_home --name myjenkins jenkins/jenkins:2.167.1
		
- another approach
	- copy the link to the latest download from the jenkins site
		http://updates.jenkins-ci.org/download/war/2.176.2/jenkins.war
	- download to the host machine
	- scp to the jenkins.war file to the VM using the following
	> $ scp ~/Downloads/jenkins.war van@10.187.215.102:/home/van/Downloads/
	- copy the jenkins.war from the VM to the running jenkins container
	> $ docker cp jenkins.war jenkins-dev:/root/
	- login to the container as root
	> $ docker exec -it -u 0 jenkins-dev bash
	- move the jenkins.war to /usr/share/jenkins folder
	> $ mv ./jenkins.war /usr/share/jenkins/jenkins.war
	- change owner and group
	> \# chown jenkins:jenkins /usr/share/jenkins/jenkins.war
	- exit and restart the container
	```
	# exit
	$ docker restart myjenkins 
	```

###### steps to start Jenkins on your own

1. pull image from vlvanchin/jenkins:latest

2. docker run -p 8080:8080 -p 50000:50000  vlvanchin/jenkins:latest

3. from the logs copy the admin key
	3b80c6f568224e6faf085319e5cce992

4. open http://localhost:8080 in browser and paste the admin key to start installation.


