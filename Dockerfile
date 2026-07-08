# Stage 1: Build the application using Maven
FROM maven:3.9.4-eclipse-temurin-21 AS build
WORKDIR /app

# 1. Copy the root parent POM
COPY pom.xml .

# 2. Copy the source code for ALL modules
COPY grpc-proto/ grpc-proto/
COPY grpc-server/ grpc-server/
COPY grpc-client/ grpc-client/

# 3. Build the entire project from the root
RUN mvn clean package -DskipTests

# Stage 2: gRPC Server Run (Server)
FROM eclipse-temurin:21-jre-alpine AS grpc-server
WORKDIR /app
COPY --from=ghcr.io/grpc-ecosystem/grpc-health-probe:v0.4.38 /ko-app/grpc-health-probe /bin/grpc_health_probe
COPY --from=build /app/grpc-server/target/grpc-server-1.0.0.jar grpc-server-1.0.0.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "grpc-server-1.0.0.jar"]

# Stage 3: gRPC Client Run
FROM eclipse-temurin:21-jre-alpine AS grpc-client
WORKDIR /app
COPY --from=build /app/grpc-client/target/grpc-client-1.0.0.jar grpc-client-1.0.0.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=dev", "grpc-client-1.0.0.jar"]