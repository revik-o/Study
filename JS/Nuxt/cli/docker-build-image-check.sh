#!/bin/bash

clear && docker build --no-cache -t test-nuxt-app:v0.0.1 . \
    && docker tag test-nuxt-app:v0.0.1 local-registry/test-nuxt-app:v0.0.1 \
    && docker image rm test-nuxt-app:v0.0.1 \
    && docker image rm local-registry/test-nuxt-app:v0.0.1