#!/bin/bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )"
cd "$DIR/.."

COMPOSE_FILE="docker/local/docker-compose.yml"

echo "🔥 Stopping docker..."
docker compose -f "$COMPOSE_FILE" down

echo "📥 Pulling latest images..."
docker compose -f "$COMPOSE_FILE" pull

echo "🚀 Starting docker..."
docker compose -f "$COMPOSE_FILE" up -d
echo "✅ Docker is started!"

echo ""
read -p "🛑 Docker is running. Press [ENTER] to stop it..."
echo ""

echo "💀 Stopping docker..."
docker compose -f "$COMPOSE_FILE" down
echo "✅ Docker is stopped!"
