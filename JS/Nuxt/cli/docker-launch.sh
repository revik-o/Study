#!/bin/bash

ARGS_STR="$*"
os_type=$(uname -s)
DOCKER_COMPOSE_FILE="docker-compose.yml"
MONGO_CONTAINER_NAME="db"
APP_CONTAINER_NAME="app"

export $(awk -F= '{output=output" "$1"="$2} END {print output}' local-env)

if [ "$os_type" == "Linux" ]; then
    if !(docker info) && (systemctl --version); then
      sudo systemctl start docker.service
    fi

    clear
fi

if [[ $ARGS_STR == *"--mongo"* || $ARGS_STR == *"-a"* ]]; then
    docker compose -f "${DOCKER_COMPOSE_FILE}" down $MONGO_CONTAINER_NAME
    docker compose -f "${DOCKER_COMPOSE_FILE}" pull $MONGO_CONTAINER_NAME
    docker compose -f "${DOCKER_COMPOSE_FILE}" up -d $MONGO_CONTAINER_NAME
fi

if [[ $ARGS_STR == *"--app"* || $ARGS_STR == *"-a"* ]]; then
    docker compose -f "${DOCKER_COMPOSE_FILE}" down $APP_CONTAINER_NAME
    docker compose -f "${DOCKER_COMPOSE_FILE}" pull $APP_CONTAINER_NAME
    docker compose -f "${DOCKER_COMPOSE_FILE}" up -d $APP_CONTAINER_NAME
fi

if [[ $ARGS_STR == *"--mongo"* || $ARGS_STR == *"--app"* || $ARGS_STR == *"-a"* ]]; then
    read -p "Press any key to close the script... "
fi

if [[ $ARGS_STR == *"-ashtd"* ]]; then
  echo "SHUTTING DOWN ALL DOCKER CONTAINERS"
  docker compose -f "${DOCKER_COMPOSE_FILE}" down

  if [[ $ARGS_STR == *"--rm-unused-volumes"* ]]; then
    docker volume prune -f

    if [[ $ARGS_STR == *"--app"* ]]; then
      docker image rm nuxt-app:latest
    fi
  fi
fi