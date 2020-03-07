# Dockerfile based on the latest centos 7 image - non privilage user image
FROM centos:latest
MAINTAINER vlvanchin@gmail.com

RUN useradd -ms /bin/bash user
RUN echo "EXPORT 192.168.0.0/24" >> /etc/exports.list
RUN yum update -y
RUN yum install -y net-tools wget

RUN cd ~ && wget --no-cookies --no-check-certificate --header "Cookie: gpw_e24=http%3A%2F%2Fwww.oracle.com%2f; oraclelicense=accept-securebackup-cookie" "https://download.oracle.com/otn-pub/java/jdk/8u201-b09/42970487e3af4f5aa5bca3f542482c60/jdk-8u201-linux-x64.rpm"

RUN yum localinstall -y ~/jdk-8u201-linux-x64.rpm

USER user

RUN cd ~ && echo "export JAVA_HOME=/usr/java/jdk1.8.0/jre" >> /home/user/.bashrc

ENV JAVA_BIN /usr/java/jdk1.8.0/jre/bin

