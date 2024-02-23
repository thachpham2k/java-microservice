#!/bin/bash

docker build -t java-microservice/build-jar . 
docker run --name jar-builder -v ./data/jarfile:/jarfiles java-microservice/build-jar 
docker rm jar-builder
docker build -t java-microservice/api-gateway -f docker/api-gateway/Dockerfile .
docker build -t java-microservice/discovery-server -f docker/discovery-server/Dockerfile .
docker build -t java-microservice/inventory-service -f docker/inventory-service/Dockerfile .
docker build -t java-microservice/order-service -f docker/order-service/Dockerfile .
docker build -t java-microservice/product-service -f docker/product-service/Dockerfile .
docker-compose up -d