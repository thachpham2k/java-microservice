FROM openjdk:17-alpine
ARG FILE_NAME=order-service
WORKDIR /app
COPY data/jarfile/${FILE_NAME}.jar app.jar
EXPOSE 8085

CMD ["java", "-jar", "app.jar"]