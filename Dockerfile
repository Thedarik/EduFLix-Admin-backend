# Stage 1: Build the application using Gradle
FROM gradle:jdk21 as build
COPY . /home/gradle/project
# Build the application
RUN gradle clean build -x test

# Stage 2: Create a minimal runtime image
FROM openjdk:21-jdk-slim

# Copy the built JAR file from the build stage
COPY --from=build /home/gradle/project/build/libs/eduflix-0.0.1-SNAPSHOT.jar eduflix.jar

# Expose the port on which the application will run
EXPOSE 8084

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "eduflix.jar"]
