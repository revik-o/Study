cmake_minimum_required(VERSION 3.26.4)
set(CMAKE_C_COMPILER "/usr/bin/gcc")
set(CMAKE_CXX_FLAGS "-Wall -Wextra")
set(CMAKE_CXX_FLAGS_DEBUG "-g")
set(CMAKE_CXX_FLAGS_RELEASE "-O3")
project(SnakeLib)
set(LIB_NAME rand32.c)

add_library(rand32 SHARED ${LIB_NAME})