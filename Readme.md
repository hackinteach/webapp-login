
# Login Web App

This webapp uses Java Tomcat Servlet with JDBC connection for MySQL database, also based on Maven project. I have configure Docker-Compose file for running this webapp on docker.

## Launching
To launch this webapp, simply type this command on the shell.
```
./start.sh
```
In case you're curious what's inside the file:
```
#!/bin/bash

mvn package
docker-compose up -d --build
```

**NOTE:**   This might take a while for the first time you run. just let Maven reload its dependencies.

## Running
This project contains 3 main parts ( plus how to run ):
#### MySQL ( : 3307 )
	
To run pure MySQL, you can run from the shell, 
*-h =  host* | *-P = port* | *-u = user* | *-p = use password*
```
run MySQL
$ mysql -h 127.0.0.1 -P 3307 -u root -p
type password
$ 12345
```
---
#### phpMyAdmin ( : 8080 )
Just put this url on the browser and it works!
 ```
 localhost:8080/
 ```
( 2 users )
 user : root / ooc
 password : 12345 / muic

 ---
#### WebApp ( : 8082 )
Same as phpMyAdmin but different port
```
localhost:8082/
```

## Docker

File :  **DockerFile**
DockerFile build only WebApp.
```
## using openjdk image ##
FROM openjdk:8u151-jdk-slim

## add current directory to /app in the container ##
ADD . /app

## map port 8082 "out" to your localhost ##
EXPOSE 8082

## set default directory ##
WORKDIR /app

## run this automatically when start the container ##
CMD ["java", "-jar", "target/ooc-webapp-1.0-SNAPSHOT.jar"]%
```
**Note** : to build this WebApp container alone, just use
```
docker build -t <name> <path>
ex : docker build -t webapp ./
```

## Docker-Compose

File : **docker-compose.yml**
This is the main part of the project where I link phpMyAdmin, MySQL, and WebApp together.

You can find out more about how to write docker-compose file [here](https://docs.docker.com/compose/compose-file/)
```
version: "3"

networks:
    webnet:
      driver: bridge

services:
  webapp:
    build: ./
    restart: on-failure
    networks:
      - webnet
    links:
      - database
    ports:
      - "8082:8082"
      
  phpmyadmin:
    image: phpmyadmin/phpmyadmin
    links:
      - database
    networks:
      - webnet
    environment:
      PMA_HOST: database
      PMA_PORT: 3306
    ports:
      - "8080:80"

  database:
    image: mysql
    restart: on-failure
    volumes:
      - ./mysql-data:/var/lib/mysql:rw
    networks:
      - webnet
    ports:
      - "3307:3306"
    environment:
      MYSQL_DATABASE: "login"
      MYSQL_USER: "ooc"
      MYSQL_PASSWORD: "muic"
      MYSQL_ROOT_PASSWORD: "12345"

```

## References

* [Docker](http://docker.com) - Driver for running all parts.
* [Maven](https://maven.apache.org/) - Dependency Management
* [Tomcat](https://tomcat.apache.org) - Servlet Management
* [JDBC](https://docs.oracle.com/javase/tutorial/jdbc/basics/index.html) - Database connection utilities
