FROM gradle:jdk17 AS build
WORKDIR /app-source
COPY build.gradle.kts settings.gradle.kts gradlew ./
COPY gradle ./gradle

COPY domain ./domain
COPY persistence ./persistence
COPY rest-api ./rest-api
COPY web-api ./web-api
COPY application ./application

RUN gradle clean :application:bootJar

FROM eclipse-temurin:17-jdk-alpine
COPY --from=build /app-source/application/build/libs/*.jar /app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
