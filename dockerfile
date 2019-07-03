FROM tomcat:9-jre11

LABEL author="Chris Wohlbrecht"

ADD target/apigateway.war /usr/local/tomcat/webapps/apigateway.war

ENV TZ=Europe/Berlin

EXPOSE 8080
EXPOSE 8009

CMD ["catalina.sh", "jpda", "run"]