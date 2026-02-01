#!/bin/sh

kubectl logs -l app=test-nestjs-apps --all-containers=true -n test-nestjs-app