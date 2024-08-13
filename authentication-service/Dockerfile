FROM openjdk:11-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the built JAR into the container
COPY build/libs/*.jar app.jar

# Expose the port the service runs on
EXPOSE 8889

# Run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]
