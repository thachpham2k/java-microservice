FROM openjdk:17-alpine
ARG FILE_NAME=inventory-service
WORKDIR /app
COPY data/jarfile/${FILE_NAME}.jar app.jar
EXPOSE 8086

CMD ["java", "-jar", "app.jar"]