# Stage 1: Use a standard Java 21 JDK image to build the application
FROM openjdk:21-slim as builder

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper and project definition files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download the project dependencies
RUN ./mvnw dependency:go-offline

# Copy the rest of the application source code
COPY src ./src

# Build the application into a JAR file
RUN ./mvnw package -DskipTests

# Stage 2: Use a lightweight Java 21 runtime image for the final container
# THE FIX IS HERE: Changed from openjdk:21-jre-slim to openjdk:21-slim
FROM openjdk:21-slim

# Set the working directory
WORKDIR /app

# Copy the executable JAR file from the builder stage
COPY --from=builder /app/target/*.jar app.jar

# Expose the port the application runs on
EXPOSE 8080

# The command to run the application when the container starts
ENTRYPOINT ["java", "-jar", "app.jar"]