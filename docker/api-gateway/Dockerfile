FROM openjdk:17-alpine

ARG FILE_NAME=api-gateway

WORKDIR /app
COPY data/jarfile/${FILE_NAME}.jar app.jar
EXPOSE 8080

CMD ["java", "-jar", "app.jar"]