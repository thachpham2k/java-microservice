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
    mysql:latest