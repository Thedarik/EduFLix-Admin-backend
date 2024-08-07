FROM gradle:jdk21 as build
COPY . .
RUN gradle clean  package -DskipTests

FROM openjdk:21-jdk-slim
COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8084
LABEL authors="islom"

ENTRYPOINT ["java", "-jar", "app.jar"]