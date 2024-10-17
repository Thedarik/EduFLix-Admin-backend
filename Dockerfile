FROM openjdk:21-jdk AS builder

# Set the working directory
WORKDIR /app

# Copy only the Gradle build files and wrapper to cache dependencies
COPY gradle/ gradle/
COPY build.gradle settings.gradle gradlew ./

# Download dependencies (caching)
RUN ./gradlew dependencies

# Copy the remaining application source code
COPY src ./src

# Build the application
RUN ./gradlew clean build

FROM openjdk:21-jdk
WORKDIR /app
COPY build/libs/eduflix-0.0.1-SNAPSHOT.jar myapp1.jar
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "myapp1.jar"]
