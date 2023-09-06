FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app
COPY . /app
RUN ["chmod", "+x", "mvnw"]
RUN ["./mvnw", "-Dmaven.test.skip", "package"]

FROM eclipse-temurin:17-jdk-jammy AS dev

WORKDIR /app
COPY --from=build /app/target/rinha-backend-2023-q3-0.1.jar /app/rinha-api.jar
EXPOSE 8080

ENTRYPOINT ["java", "-Xms750m", "-Xmx750m", "-jar", "rinha-api.jar"]