FROM openjdk:8u151-jdk-slim
ADD . /app
EXPOSE 8082
WORKDIR /app

CMD ["java", "-jar", "target/ooc-webapp-1.0-SNAPSHOT.jar"]