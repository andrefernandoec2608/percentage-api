#FROM eclipse-temurin:21-jdk
#WORKDIR /app
#COPY . .
#RUN ./mvnw clean package -DskipTests
#CMD ["java", "-jar", "target/percentage-api-0.0.1-SNAPSHOT.jar"]


# Etapa 1: build con Maven
FROM maven:3.9.6-eclipse-temurin-21 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Etapa 2: solo JDK para ejecutar
FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY --from=builder /app/target/percentage-api-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]