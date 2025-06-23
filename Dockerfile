# Build Stage
FROM maven:3.8.4-openjdk-17-slim AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package
#-DskipTests
#RUN mvn clean install

FROM postgres:14.7
# Create a script to initialize the database and create the user
COPY init_db.sh /docker-entrypoint-initdb.d/

# Runtime Stage
FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
RUN apt-get -q update && apt-get -qy install netcat

COPY --from=build /app/target/biblio-dev-0.0.1-SNAPSHOT.jar .
#COPY wait-for-it.sh /usr/local/bin/
#RUN chmod +x /usr/local/bin/wait-for-it.sh
#CMD ["wait-for-it.sh", "db:5432", "--", "java", "-jar", "biblio-dev-0.0.1-SNAPSHOT.jar"]
CMD ["java", "-jar", "biblio-dev-0.0.1-SNAPSHOT.jar"]
