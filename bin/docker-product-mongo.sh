mkdir -p ~/data/mongo-data
docker run -d \
    --name mongo-product \
    -p 27017:27017 \
    --restart always \
    -v ~/data/mongo-data:/data/db \
    mongo:4.4.14-rc0-focal
