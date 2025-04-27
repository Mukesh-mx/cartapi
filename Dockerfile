# Use OpenJDK as base image
FROM openjdk:21-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file into container
COPY target/cartapi-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

# Run the jar
ENTRYPOINT ["java", "-jar", "app.jar"]
