#!/bin/sh

ARGS_STR="$*"

case "$ARGS_STR" in
    *"--prune"*)
        docker image rm local-registry/test-nestjs-app:v0.0.1
        ;;
esac

clear && docker build --no-cache -t test-nestjs-app:v0.0.1 . \
    && docker tag test-nestjs-app:v0.0.1 local-registry/test-nestjs-app:v0.0.1 \
    && docker image rm test-nestjs-app:v0.0.1