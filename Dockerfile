FROM ubuntu
MAINTAINER narendra.wls123@gmail.com
ADD target/MavenWebProject-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/
EXPOSE 8080
CMD ["catalina.sh", "run"]
