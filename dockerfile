FROM openjdk:11-jdk

LABEL autor="Chris Wohlbrecht"

ENV TZ=Europe/Berlin

ARG JAR_FILE=target/apigateway.jar
ADD ${JAR_FILE} apigateway.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/apigateway.jar"]
