package com.sql.controller

import io.ktor.http.HttpStatusCode
import io.ktor.server.response.respond
import io.ktor.server.routing.RoutingCall

suspend fun httpErrorHandler(
    routingCall: RoutingCall,
    status: HttpStatusCode? = null,
    message: String? = null,
    lambda: suspend () -> Unit
) {
    try {
        lambda()
    } catch (exception: IllegalArgumentException) {
        routingCall.respond(
            status ?: HttpStatusCode.BadRequest,
            mapOf("error" to (message ?: exception.message ?: "Invalid request"))
        )
        throw exception
    }
}
