FROM openjdk:17-alpine
WORKDIR /app
COPY target/*.jar server.jar
CMD ["java", "-jar", "server.jar"]