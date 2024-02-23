FROM openjdk:17-alpine
ARG FILE_NAME=product-service
WORKDIR /app
COPY data/jarfile/${FILE_NAME}.jar app.jar
EXPOSE 8084

CMD ["java", "-jar", "app.jar"]