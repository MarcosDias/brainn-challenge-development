FROM openjdk:11-jdk

WORKDIR /app

COPY . /app

RUN ./mvnw clean install
	
ENTRYPOINT  /usr/bin/java -jar  target/java-spring-mongo-0.0.1-SNAPSHOT.jar