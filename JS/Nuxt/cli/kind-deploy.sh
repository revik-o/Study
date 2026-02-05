#!/bin/sh

helm install --debug --dry-run test-release ./nuxt-app-stack && helm install nuxt-release ./nuxt-app-stack
