package com.sql.controller

import io.ktor.server.application.Application

object Controller {
    val API_PREFIX = "/api/v1"
}

fun Application.configureController() {
    configureUserRouting()
}