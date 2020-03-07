# use python runtime
FROM python:2.7-slim

# set the working dir
WORKDIR /app

# copy the app
COPY . /app

# install any needed packages as requirements
RUN pip install --trusted-host pypi.python.org -r requirements.txt

# make port 80 open to the world outside
EXPOSE 80

# define env variables
ENV NAME world

# RUN app.py when container launches
CMD ["python","app.py"]



