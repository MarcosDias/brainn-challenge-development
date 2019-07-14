FROM adoptopenjdk/openjdk11:latest

WORKDIR /opt

COPY target/*.jar /opt/app.jar

EXPOSE 3000

ENTRYPOINT ["java","-jar","app.jar"]