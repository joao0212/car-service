FROM openjdk:17-jdk-slim-buster
EXPOSE 8080
ADD /target/car-service-0.0.1-SNAPSHOT.jar car-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=prod", "-jar", "car-service.jar"]