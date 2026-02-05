#!/bin/bash

export $(awk -F= '{output=output" "$1"="$2} END {print output}' local-env)

npm run build