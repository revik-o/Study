#!/bin/sh

export $(awk -F= '{output=output" "$1"="$2} END {print output}' local-env)

npm i
npm run migration:generate migrations/v20260131 
sleep 5
npm run migration:run