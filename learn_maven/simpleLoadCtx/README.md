# Sample - Loading Spring Application Context
This maven sample is to demo how to get application context and load the beans through XML configuration.

## POM configuration
This project uses Spring-core, spring-beans and spring-context-support library and its dependency. Include the 'maven-jar-plugin' for create the jar artifact

## Bean definition
Create HelloWorld bean class with the setter/getter methods for the name property

## Bean configuration
Create the application context, 'Spring-Module.xml' file and define the HelloWorld as the bean. This file is create in 'src/main/resources' folder

## Build and execute application

### execute as java application eclipse ide
On App.java file, right click and 'Run As' -> 'Java Application'

### build jar 
from the project folder, execute 
```
$ mvn clean package
```

### How to run *simpleLoadCtx* application jar in command line

```
$ java -cp .:/home/vanchin/.m2/repository/commons-logging/commons-logging/1.2/commons-logging-1.2.jar:/opt/spring/libs/*:target/simpleLoadCtx-1.0-SNAPSHOT.jar com.my.app.App

```
----
# How to configure application context as Java class

## required libraries

Add the following spring api to the pom.xml
* spring-core
* spring-context

Add the following java api to the pom.xml
* cglib

## Define configuration in java

Create a java class, 'AppConfig' in any folder. Annotate this class as 'Configuration'

```
@Configuration
```

Define a method that returns an instance of the required Bean (java class), which is annotated with 'Bean' and initialize the 'name' attribute. Use this value in '.getBean' method.

```
@Bean(name="greeting")
```

