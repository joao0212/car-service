FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ADD /target/car-service-0.0.1-SNAPSHOT.jar car-service.jar
ENTRYPOINT ["java", "-jar", "car-service.jar"]