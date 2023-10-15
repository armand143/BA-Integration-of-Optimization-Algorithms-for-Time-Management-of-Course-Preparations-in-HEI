# Build stage
FROM openjdk:17-slim as builder

# Install Maven
RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to the container
COPY ./rest-serviceS/timeOpta/pom.xml .

# Copy source code into the container
COPY ./rest-serviceS/timeOpta/target/timeOpta-0.0.1-SNAPSHOT.jar ./app.jar

# Build the app
RUN mvn package

# Use the official Java runtime as a base image for the built app
# FROM openjdk:17-jre-slim
FROM openjdk:17-jdk-slim

# Copy the built app from the builder image
COPY --from=builder /app/app.jar /usr/app/timeOpta-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "/usr/app/timeOpta-0.0.1-SNAPSHOT.jar"]

