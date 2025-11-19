# using openJDK based image
FROM eclipse-temurin:21-jdk

# set working directory
WORKDIR /app

# copy the jar file to the working directory , source is demo-0.0.1-SNAPSHOT.jar and destination is app.jar
COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

# this tells docker that the application inside container listens on port 8080
EXPOSE 8080

# commands to run the app
ENTRYPOINT ["java", "-jar", "app.jar"]