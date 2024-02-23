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
    mysql:latest
