# Dockerfile based on the latest centos 7 image - non privilage user image
FROM centos:latest
MAINTAINER vlvanchin@gmail.com

RUN useradd -ms /bin/bash user
RUN echo "EXPORT 192.168.0.0/24" >> /etc/exports.list

USER user


