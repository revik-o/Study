package com.sql.controller

import io.ktor.server.application.Application
import io.ktor.server.routing.post
import io.ktor.server.routing.routing
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.http.*
import com.sql.controller.Controller.API_PREFIX
import com.sql.dao.request.LoginRequestDTO
import com.sql.dao.request.RegisterRequestDTO
import io.ktor.server.application.log

fun Application.configureUserRouting() {
    routing {
        post("${API_PREFIX}/user/register") {
            val request = call.receive<RegisterRequestDTO>()

            httpErrorHandler(call) {
                require(request.email.isNotBlank()) { "Email cannot be blank" }
                require(request.email.contains("@")) { "Invalid email format" }
                require(request.password.length >= 8) { "Password must be at least 8 characters long" }
                require(request.password == request.passwordVerification) { "Passwords do not match" }
                require(request.firstName.isNotBlank() && request.lastName.isNotBlank()) { "Name cannot be blank" }
            }

            log.info("Registering user: ${request.email}")
            call.respond(HttpStatusCode.Created, mapOf("message" to "User successfully registered!"))
        }

        post("${API_PREFIX}/user/login") {
            val request = call.receive<LoginRequestDTO>()

            httpErrorHandler(call, HttpStatusCode.Forbidden) {
                require(request.login.isNotBlank()) { "Login cannot be blank" }
                require(request.password.isNotBlank()) { "Password cannot be blank" }
            }
        }
    }
}