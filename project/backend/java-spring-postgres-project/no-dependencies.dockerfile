FROM maven:3-jdk-11 as builder

RUN mkdir -p /build
WORKDIR /build
COPY pom.xml /build

RUN mvn -B dependency:resolve dependency:resolve-plugins

COPY src /build/src

RUN mvn package

#####

FROM openjdk:11-slim as runtime

ENV APP_HOME /app

WORKDIR $APP_HOME

COPY --from=builder /build/target/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java","-jar","app.jar"]