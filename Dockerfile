# Stage 1: Build the application using Gradle
FROM gradle:jdk21 as build
ARG JAR_FILE=build/*.jar
COPY ./build/libs/eduflix-0.0.1-SNAPSHOT-plain.jar app.jar
ENTRYPOINT["java","-jar","app.jar"]