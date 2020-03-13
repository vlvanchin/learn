# this document give tips to docker

## lesson 2 

This is to test docker to create/commit/push docker image to the repo.

### make director lesson2 and create the following files 

create the following 

```
$ mkdir lesson2
$ cd lesson2
```

#### create the app.py 

```
from flask import Flask
from redis import Redis, RedisError
import os
import socket

redis = Redis(host="redis", db=0, socket_connect_timeout=2, socket_timeout=2)

app = Flask(__name__)

@app.route("/")
def hello():
    try:
        visits = redis.incr("counter")
    except RedisError:
        visits = "<i>cannot connect to Redis, counter disabled</i>"

    html = "<h3>Hello {name}!</h3>" \
           "<b>Hostname:</b> {hostname}<br/>" \
           "<b>Visits:</b> {visits}"
    return html.format(name=os.getenv("NAME", "world"), hostname=socket.gethostname(), visits=visits)

if __name__ == "__main__":
    app.run(host='0.0.0.0', port=80)

```

#### create the Dockerfile to create the image from python

```
FROM python:2.7-slim

WORKDIR /app

ADD . /app

RUN pip install --trusted-host pypi.python.org -r requirements.txt

EXPOSE 80

ENV NAME World

CMD ["python", "app.py"]
```

#### create the requirements.txt file to support the python code

```
Flask
Redis
```

### now step to build the docker image

```
$ docker build -t friendlyhello . 
```

for building with using the cache contents try the above command with `--no-cache` option.

### To run the image as a container instance with the host port as 4000. once the container is up... enter http://localhost:4000 in the browser to view the application

```
$ docker run -p 4000:80 friendlyhello
```

to run the container in port 80 in detached mode (-d), view the application using http://localhost or `curl http://localhost`

```
$ docker run -d -p 80:80 friendlyhello
```

to run the container in any random port run the following with the `-P` option.

To stop the container(s) use the following command

```
$ docker stop <container_name1 container_name2>
```

#### how to tag and push to remote docker hub using the new created image

use the following command to tag

```
$ docker tag <container-name> <userid/container-name:label>
```

use the following command to push to docker hub, pre-condition: user docker login to login to the remote hub

```
$ docker push <tagged-image-name>
```

#### how to view the layers of the created image

use the following command to view the layers

```
$ docker history <image-name>
```

#### how to remove the unwanted containers 

use the following command to remove all the stopped containers

```
$ docker rm $(docker ps -a -q)
```

## Lesson 3

### create a separate folder lessson3 and `cd` to it

### compose a docker that could spin up 3 containers of the pushed images in lesson2

write a `docker-compose.yml` with the following

```
$ cat docker-compose.yml
version: "3"
services:
  web:
    # replace the username/repo:tag
    image: vlvanchin/get_started:part2
    deploy:
      replicas: 3
      resources:
        limits:
          cpus: "0.1"
          memory: 50M
      restart_policy:
        condition: on-failure
    ports:
      - "80:80"
    networks:
      - webnet
networks:
  webnet:
```

the above code configures the image to be used for spinning the containers, defines the number of replicas as 3, resources like cpu and memory, the restart-policy to automatically restart on failures, defines the host:container ports to 80:80 and the network as webnet

### Initialize to Swarm mode

```
$ docker swarm init
```

This will initalize the current node as the manager node, and will display the command to create worker nodes to join this manager

### deploy the stack of containers as an application (give a name)

```
$ docker stack deploy -c docker-compose.yml getstartedlab
```

here sub-command `stack` refers to the containers, `deploy` is the action of the sub-command. `-c` is the configuration that is taken from docker-compose.yml, *getstartedlab* is the name of the application.

when run, this will create the following

* creates network *getstartedlab_webnet*
* creates service *getstartedlab_web*

### how to view the list of services

```
$ docker stack ls
```

This will show the services and orchestrator and the name of the service

NAME                SERVICES            ORCHESTRATOR
getstartedlab       1                   Swarm


### how to view the details of the stack service

this is shown in 2 methos

```
$ docker service ls

or

$ docker stack services getstartedlab
```

This will list down the services (application) with columns like ID, NAME, MODE, REPLICAS, IMAGE AND PORTS

ID                  NAME                MODE                REPLICAS            IMAGE                         PORTS
otex9eq07w61        getstartedlab_web   replicated          3/3                 vlvanchin/get_started:part2   \*:80->80/tcp

access the application in brower using *http://localhost* and keep hitting `refresh` button and you will notice that response is from different replicas

### how to view the list of container in the application

```
$ docker service ps getstartedlab_web
```

This ps is the process status, list of all containers in application (getstartedlab_web) stack.

ID                  NAME                  IMAGE                         NODE                 DESIRED STATE       CURRENT STATE           ERROR               PORTS
hx66smz60fny        getstartedlab_web.1   vlvanchin/get_started:part2   van-Aspire-E5-491G   Running             Running 8 minutes ago
gm0wtrr8wyfe        getstartedlab_web.2   vlvanchin/get_started:part2   van-Aspire-E5-491G   Running             Running 8 minutes ago
vbqhrksd6m02        getstartedlab_web.3   vlvanchin/get_started:part2   van-Aspire-E5-491G   Running             Running 8 minutes ago


### update and reconfigure the stack

if you change the number of replicas to 5 in the docker-compose.yml file, then run the following command to redeploy

```
$ docker stack deploy -c docker-compose.yml getstartedlab
```

### how to remove the stack service

```
$ docker stack rm getstartedlab
```

The above command will remove the stack service by 

* removes service *getstartedlab_web*
* removes network *getstartedlab_webnet*


### how to leave the swarm 

leaving swarm as an admin

```
$ docker swarm leave --force
```

leaving swarm as a worker node

```
$ docker swarm leave
```

