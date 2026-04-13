FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
ENTRYPOINT ["sh", "-c", "java -jar app.jar && sleep infinity"]