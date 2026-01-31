#!/bin/bash

ARGS_STR="$*"

kubectl apply -f k8s-secret.yaml -n test-nestjs-app
kubectl apply -f k8s-pvc.yaml -n test-nestjs-app
kubectl apply -f k8s-deployment.yaml -n test-nestjs-app
kubectl apply -f k8s-service.yaml -n test-nestjs-app

kubectl wait --for=condition=ready pod -l app=test-nestjs-apps --timeout=10s -n test-nestjs-app

kubectl get all -n test-nestjs-app

kubectl logs -f -l app=test-nestjs-apps -n test-nestjs-app

xdg-open "http://localhost:8080"

kubectl port-forward service/test-nestjs-apps-v0-0-1 8080:80 -n test-nestjs-app

case "$ARGS_STR" in
    *"--force-restart"*)
        kubectl rollout restart deployment test-nestjs-apps-v0-0-1 -n test-nestjs-app
        ;;
esac


case "$ARGS_STR" in
    *"-rm"*)
        kubectl delete -f k8s-deployment.yaml -n test-nestjs-app && \
            kubectl delete -f k8s-service.yaml -n test-nestjs-app && \
            kubectl delete all --all -n test-nestjs-app
        ;;
esac
