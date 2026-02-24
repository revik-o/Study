cmake_minimum_required(VERSION 3.31)

project(MyGlobalIPProj LANGUAGES C)

set(CMAKE_C_STANDARD 23)
set(CMAKE_C_STANDARD_REQUIRED ON)

find_package(PkgConfig REQUIRED)

pkg_check_modules(GTK4 REQUIRED gtk4)
pkg_check_modules(WEBKIT REQUIRED webkitgtk-6.0)
pkg_check_modules(SOUP REQUIRED libsoup-3.0)
pkg_check_modules(JSON REQUIRED json-glib-1.0)

add_executable(MyGlobalIP src/main.c)

target_include_directories(MyGlobalIP PRIVATE 
    ${GTK4_INCLUDE_DIRS}
    ${WEBKIT_INCLUDE_DIRS}
    ${SOUP_INCLUDE_DIRS}
    ${JSON_INCLUDE_DIRS}
)

target_link_libraries(MyGlobalIP PRIVATE 
    ${GTK4_LIBRARIES} 
    ${WEBKIT_LIBRARIES}
    ${SOUP_LIBRARIES}
    ${JSON_LIBRARIES}
)

target_compile_options(MyGlobalIP PRIVATE -Wall -Wextra -Werror -O3 -g)
