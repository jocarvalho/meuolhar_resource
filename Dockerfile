# syntax=docker/dockerfile:experimental
FROM maven:3.8.4-openjdk-17 as build
ENV APP_HOME=/usr/src/app
RUN mvn --version


COPY mvnw .
COPY pom.xml .
COPY src src

RUN mvn clean package -DskipTests
RUN echo $(ls -1 .)
ARG JAR_FILE=target/resource-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]