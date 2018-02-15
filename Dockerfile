FROM openjdk:8u151-jdk-slim
ADD . /app
EXPOSE 8082
WORKDIR /app
#env VIRTUAL_PORT 8080
#env VIRTUAL_HOST hackinteach.com
#env LETSENCRYPT_HOST hackinteach.com
#env LETSENCRYPT_EMAIL webmaster@hackinteach.com

CMD ["java", "-jar", "target/ooc-webapp-1.0-SNAPSHOT.jar"]