
#!/bin/bash
#
# to start the jenkins server
#
# starting jenkins container
docker run -d --rm -p 8080:8080 -p 50000:50000 -v jenkins-data:/var/jenkins_home --name jenkins jenkins/jenkins:latest
