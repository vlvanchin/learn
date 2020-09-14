
#!/bin/bash
#
# to start the jenkins server
#
# pull the jenkins/jenkins:latest image
docker pull jenkins/jenkins:latest

# starting jenkins container
docker run -d --rm -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home --name jenkins jenkins/jenkins:latest
