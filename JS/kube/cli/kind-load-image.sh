#!/bin/sh

kind load docker-image local-registry/test-nestjs-app:v0.0.1 \
    --name test-nestjs-app -n test-nestjs-app