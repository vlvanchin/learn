FROM java:8
VOLUME /tmp
ADD simpleapp-0.1-SNAPSHOT.jar .

ENV CLASSPATH=.:simpleapp-0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "simpleapp-0.1-SNAPSHOT.jar"]
