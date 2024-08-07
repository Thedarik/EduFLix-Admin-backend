# Stage 1: Use a base image with Java 21 and a minimal runtime
FROM eclipse-temurin:21-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy the built JAR file into the container
COPY build/libs/*.jar app.jar

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port on which the application will run
EXPOSE 8084
