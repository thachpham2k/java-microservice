FROM maven:3.9.6-eclipse-temurin-17-alpine AS builder

ARG DIRECTORYS="api-gateway discovery-server inventory-service order-service product-service"

WORKDIR /app

COPY pom.xml .

COPY api-gateway/pom.xml ./api-gateway/
COPY discovery-server/pom.xml ./discovery-server/
COPY inventory-service/pom.xml ./inventory-service/
COPY order-service/pom.xml ./order-service/
COPY product-service/pom.xml ./product-service/

RUN mvn dependency:go-offline -B

COPY api-gateway/src ./api-gateway/src
COPY discovery-server/src ./discovery-server/src
COPY inventory-service/src ./inventory-service/src
COPY order-service/src ./order-service/src
COPY product-service/src ./product-service/src

RUN mvn package -DskipTests

RUN mkdir -p jarfiles; for direct in $DIRECTORYS; do \
        cp /app/$direct/target/*.jar ./jarfiles/$direct.jar; \
    done
# Stage 2: Create a final image to save the JAR file
FROM alpine:latest

WORKDIR /temp

COPY --from=builder /app/jarfiles/*.jar ./jarfiles/

RUN mkdir -p /jarfiles

CMD ["cp", "-r", "/temp/jarfiles", "/"]