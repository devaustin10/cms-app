# Use the official OpenJDK image
FROM bellsoft/liberica-openjdk-alpine:17

# Set the working directory in the container
WORKDIR /springServer

# Copy the Spring Boot JAR file into the container
COPY target/server-0.0.1-SNAPSHOT.jar /springServer

# Expose the port that the application will run on
EXPOSE 8080

# Command to run the Spring Boot application
CMD ["java", "-jar", "server-0.0.1-SNAPSHOT.jar"]