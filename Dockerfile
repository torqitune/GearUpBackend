# using openJDK based image
FROM eclipse-temurin:21-jdk

# set working directory
WORKDIR /app

# copy entire project
COPY . .

# Build the JAR inside the container
RUN ./mvnw clean package -DskipTests

# Run the JAR
CMD ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]