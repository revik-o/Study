#!/bin/bash

export CC=clang
export CXX=clang++

rm -rf ./build
sudo apt update && sudo apt install clang clang++ autoconf build-essential cmake git pkg-config libgtk-4-dev libwebkitgtk-6.0-dev
clear
cmake -B build -S . && cmake --build build --verbose