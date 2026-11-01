# Use the official Gradle image for the build stage
FROM gradle:7.4.2-jdk17 as build

# Set the working directory inside the container
WORKDIR /home/gradle/project

# Copy the Gradle configuration files and source code into the container
COPY --chown=gradle:gradle build.gradle.kts gradlew /home/gradle/project/
COPY --chown=gradle:gradle gradle /home/gradle/project/gradle
COPY --chown=gradle:gradle src /home/gradle/project/src

# Build the application
RUN ./gradlew build -x test

# Use OpenJDK for the runtime stage
FROM openjdk:17-jdk-slim

# Copy the built jar file from the build stage
COPY --from=build /home/gradle/project/build/libs/*.jar /usr/local/lib/app.jar

# Expose the port the app runs on
EXPOSE 8000

# Run the application
ENTRYPOINT ["java","-jar","/usr/local/lib/app.jar"]