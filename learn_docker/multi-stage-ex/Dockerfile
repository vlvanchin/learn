FROM maven:3.8.4-jdk-11-slim as builder
WORKDIR /usr/src/app
COPY . /usr/src/app
VOLUME /usr/src/app
RUN mvn clean install -DskipTests


FROM maven:3.8.4-jdk-11-slim
WORKDIR /
COPY --from=builder /usr/src/app/target/my-app-1.0-SNAPSHOT.jar /app.jar
CMD ["java","-jar","/app.jar"]
