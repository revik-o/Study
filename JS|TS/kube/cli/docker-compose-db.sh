#!/bin/sh

DOCKER_COMPOSE_FILE="docker-compose.yml"
CONTAINER_NAME="db"
CONTAINER_LABEL="test-nestjs-app-db"

export $(awk -F= '{output=output" "$1"="$2} END {print output}' local-env)

docker compose -f "${DOCKER_COMPOSE_FILE}" down $CONTAINER_NAME && \
    docker compose -f "${DOCKER_COMPOSE_FILE}" pull $CONTAINER_NAME && \
    docker compose -f "${DOCKER_COMPOSE_FILE}" up -d $CONTAINER_NAME

sleep 1s

docker exec -it $CONTAINER_LABEL sh

docker compose -f "${DOCKER_COMPOSE_FILE}" down $CONTAINER_NAME

docker volume prune