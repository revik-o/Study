#!/bin/sh

ARGS_STR="$*"
DOCKER_COMPOSE_FILE="docker-compose.yml"
CONTAINER_NAME="test-nestjs-app"

export $(awk -F= '{output=output" "$1"="$2} END {print output}' local-env)

docker compose -f "${DOCKER_COMPOSE_FILE}" down db && \
    docker compose -f "${DOCKER_COMPOSE_FILE}" pull db && \
    docker compose -f "${DOCKER_COMPOSE_FILE}" up -d db

./cli/app-migration.sh

docker compose -f "${DOCKER_COMPOSE_FILE}" down && \
    docker compose -f "${DOCKER_COMPOSE_FILE}" pull && \
    docker compose -f "${DOCKER_COMPOSE_FILE}" up -d

sleep 1s

docker logs $CONTAINER_NAME
xdg-open "http://localhost:8080"
docker exec -it $CONTAINER_NAME sh

case "$ARGS_STR" in
    *"--down"*)
        docker compose -f "${DOCKER_COMPOSE_FILE}" down && rm -rf migrations
        ;;
esac