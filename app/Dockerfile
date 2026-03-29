# Stage 1: Build
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copy the entire project root
COPY . .

# Make mvnw executable
RUN chmod +x ./mvnw

# Build the project
RUN ./mvnw clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21
WORKDIR /app

# Copy built JAR
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
