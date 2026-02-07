cmake_minimum_required(VERSION 3.31)

project(MyGlobalIPProj LANGUAGES C)

set(CMAKE_C_STANDARD 23)
set(CMAKE_C_STANDARD_REQUIRED ON)

find_package(PkgConfig REQUIRED)

pkg_check_modules(GTK4 REQUIRED gtk4)
pkg_check_modules(WEBKIT REQUIRED webkitgtk-6.0)

add_executable(MyGlobalIP src/main.c)

target_include_directories(MyGlobalIP PRIVATE ${GTK4_INCLUDE_DIRS} ${WEBKIT_INCLUDE_DIRS})

target_link_libraries(MyGlobalIP PRIVATE ${GTK4_LIBRARIES} ${WEBKIT_LIBRARIES})

target_compile_options(MyGlobalIP PRIVATE -Wall -Wextra -Werror -O3 -g)
