#!/bin/sh

ARGS_STR="$*"

if [ "$(basename "$PWD")" != "cli" ]; then
    cd cli || exit 1
fi

kind create cluster --name test-nestjs-app --config kind-config.yaml

clear

kubectl cluster-info --context kind-test-nestjs-app

kubectl get nodes

kubectl get pods -A

kubectl create namespace test-nestjs-app

case "$ARGS_STR" in
    *"-rm"*)
        kind delete cluster --name test-nestjs-app
        ;;
esac