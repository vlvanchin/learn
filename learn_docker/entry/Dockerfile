# Dockerfile based on the latest centos 7 image - non privilage user image
FROM centos:latest
MAINTAINER vlvanchin@gmail.com

RUN useradd -ms /bin/bash user

ENTRYPOINT echo "this command will display this msg on every container that run from it"

USER user


