FROM openjdk:17-alpine
ARG FILE_NAME=discovery-server
WORKDIR /app
COPY data/jarfile/${FILE_NAME}.jar app.jar
EXPOSE 8761

CMD ["java", "-jar", "app.jar"]