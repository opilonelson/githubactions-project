FROM eclipse-temurin:8-jdk-alpine AS build
WORKDIR /app
COPY . /app
RUN ./gradlew build -x test || { echo 'Gradle build failed — ensure Gradle wrapper or Gradle is available.'; exit 1; }

FROM eclipse-temurin:8-jre-alpine
WORKDIR /app
COPY --from=build /app/build/libs/*-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/app.jar"]
