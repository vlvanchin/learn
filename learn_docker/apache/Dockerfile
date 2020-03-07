# this image will start apaache service in each container
FROM centos:latest
MAINTAINER vlvanchin@gmail.com

RUN yum update -y
RUN yum install -y httpd net-tools

RUN echo "this a custome index file build during image creation " > /var/www/html/index.html

EXPOSE 80
ENTRYPOINT apachectl "-DFOREGROUND" 


