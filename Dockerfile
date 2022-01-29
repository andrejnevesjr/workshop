FROM openjdk:14-jdk-alpine
EXPOSE 8080
COPY target/*.jar restapi.jar
ENTRYPOINT ["java","-jar","/restapi.jar"]