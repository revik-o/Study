#!/bin/sh

kind create cluster --name test-nuxt-app --config cli/kind-config.yaml

clear

kubectl cluster-info --context kind-test-nuxt-app

kubectl get nodes

kubectl get pods -A

kubectl create namespace test-nuxt-app

docker build --no-cache -t local-registry/test-nuxt-app:v0.0.1 . && \
    kind load docker-image local-registry/test-nuxt-app:v0.0.1 \
        --name test-nuxt-app -n test-nuxt-app && \
            docker image rm local-registry/test-nuxt-app:v0.0.1