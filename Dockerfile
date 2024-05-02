FROM openjdk:17-jdk-alpine
EXPOSE 8080

RUN apk add --no-cache libreoffice ttf-dejavu

RUN mkdir -p /tmp/files

ARG JAR_FILE=target/playground-0.0.1.jar
ADD ${JAR_FILE} playground.jar

ENTRYPOINT ["java","-jar","/playground.jar"]