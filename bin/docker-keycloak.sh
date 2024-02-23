docker run -d\
    -p 8084:8080 \
    -e KEYCLOAK_ADMIN=admin \
    -e KEYCLOAK_ADMIN_PASSWORD=admin \
    quay.io/keycloak/keycloak:23.0.6 start-dev

# login using browser with admin:admin