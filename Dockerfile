FROM openjdk:17-jdk-alpine
LABEL maintainer="dirbeq"
EXPOSE 8080

ARG JAR_FILE=target/playground-0.0.1.jar
ADD ${JAR_FILE} playground.jar

ENTRYPOINT ["java","-jar","/playground.jar"]