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
to run the container in port 80 in detached mode (-d), view the application using http://localhost

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


