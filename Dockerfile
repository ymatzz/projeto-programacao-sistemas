# Backend Dockerfile (./backend/Dockerfile)
FROM maven:3.9-eclipse-temurin-17

WORKDIR /app
COPY pom.xml .
COPY src ./src

# Package the application
RUN mvn clean package -DskipTests

# Run the application
CMD ["java", "-jar", "target/*.jar"]
