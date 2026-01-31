#!/bin/sh

ARGS_STR="$*"

kind create cluster --name test-nestjs-app

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