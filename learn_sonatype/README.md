# SonaType 
This page helps to use SonaType repo

## Configuration and Execution
Here are the steps to configure and run Sonatype repo

### To start the container
* docker run -d -p 8081:8081 --rm sonatype/nexus3
 

### to stop the container
* docker stop -t 120 *container_name*

## To start with persistent storage 

### create a volume using Docker
* docker volume create --name nexus-data

### run docker with the mapping to docker volume
* docker run -d -p 8081:8081 --name nexus -v nexus-data:/nexus-data sonatype/nexus3


