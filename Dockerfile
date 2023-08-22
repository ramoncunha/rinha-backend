FROM eclipse-temurin:17-jdk-jammy AS build

WORKDIR /app
COPY . /app
RUN ["chmod", "+x", "mvnw"]
RUN ["./mvnw", "install"]

FROM eclipse-temurin:17-jdk-jammy AS dev

WORKDIR /app
COPY --from=build /app/target/rinha-backend-2023-q3-0.1.jar /app/rinha-api.jar
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "rinha-api.jar"]