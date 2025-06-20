# Build Stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime Stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/biblio-dev-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "biblio-dev-0.0.1-SNAPSHOT.jar"]