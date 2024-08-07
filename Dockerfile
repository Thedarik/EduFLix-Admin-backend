# Use a base image that includes Java
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY build/libs/*.jar app.jar

# Expose the port the app runs on
EXPOSE 8085

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
