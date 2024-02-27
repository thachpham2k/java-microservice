#zipkin
docker run -d -p 9411:9411 openzipkin/zipkin

# keycloak
docker run -d\
    -p 8080:8080 \
    -e KEYCLOAK_ADMIN=admin \
    -e KEYCLOAK_ADMIN_PASSWORD=admin \
    quay.io/keycloak/keycloak:23.0.7 start-dev
# login using browser with admin:admin

# inventory service
mkdir -p ~/data/mysql-inventory
docker run -d \
    --name mysql-inventory \
    -p 3307:3306 \
    --restart always \
    -e MYSQL_DATABASE=inventory_service \
    -e MYSQL_USER=mysql \
    -e MYSQL_PASSWORD=mysql \
    -e MYSQL_ROOT_PASSWORD=mysql \
    -v ~/data/mysql-inventory:/var/lib/mysql \
    mysql:8.3.0

# order service
mkdir -p ~/data/mysql-order
docker run -d \
    --name mysql-order \
    -p 3306:3306 \
    --restart always \
    -e MYSQL_DATABASE=order_service \
    -e MYSQL_USER=mysql \
    -e MYSQL_PASSWORD=mysql \
    -e MYSQL_ROOT_PASSWORD=mysql \
    -v ~/data/mysql-order:/var/lib/mysql \
    mysql:8.3.0
    
# product service
mkdir -p ~/data/mongo-data
docker run -d \
    --name mongo-product \
    -p 27017:27017 \
    --restart always \
    -v ~/data/mongo-data:/data/db \
    mongo:4.4.14-rc0-focal
