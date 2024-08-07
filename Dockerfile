FROM gradle:jdk21 as build
COPY . .
RUN gradle clean build -x test
RUN ls /home/gradle/build/libs

FROM openjdk:21-jdk-slim
COPY --from=build /home/gradle/build/libs/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
LABEL authors="islom"

ENTRYPOINT ["java", "-jar", "app.jar"]
