cmake_minimum_required(VERSION 3.26.4)
set(CMAKE_C_COMPILER "/usr/bin/g++")
project(SnakeLib)
set(LIB_NAME lib.cpp)

add_library(lib SHARED ${LIB_NAME})