package com.sql

import com.fasterxml.jackson.databind.SerializationFeature
import com.sql.database.configureDatabase
import io.ktor.http.*
import io.ktor.openapi.*
import io.ktor.serialization.jackson.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.cors.routing.*
import io.ktor.server.plugins.openapi.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.sql.controller.configureController
import com.sql.utils.DependencyInjectionContainer.register

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    run {
        register(log)
    }

    configureDatabase()
    configureServer()
    configureSecurity()
    configureSerialization()
    configureController()
    configureHealthCheck()
}

fun Application.configureServer() {
    val baseUrl = environment.config.property("ktor.deployment.base-url").getString()

    routing {
        openAPI(path = "openapi") {
            info = OpenApiInfo(title = "API", version = "1.0.0")
        }
    }
    install(CORS) {
        allowMethod(HttpMethod.Options)
        allowMethod(HttpMethod.Put)
        allowMethod(HttpMethod.Delete)
        allowMethod(HttpMethod.Patch)

        allowHeader(HttpHeaders.Authorization)
        allowHeader(HttpHeaders.ContentType)

        allowHost(baseUrl)
    }
}

fun Application.configureSerialization() {
    install(ContentNegotiation) {
        jackson {
            enable(SerializationFeature.INDENT_OUTPUT)
        }
    }
}

fun Application.configureSecurity() {
    val jwtAudience = environment.config.property("jwt.audience").getString()
    val jwtRealm = environment.config.property("jwt.realm").getString()
    val jwtSecret = environment.config.property("jwt.secret").getString()
    val jwtIssuer = environment.config.property("jwt.issuer").getString()

    authentication {
        jwt {
            realm = jwtRealm
            verifier(
                JWT
                    .require(Algorithm.HMAC256(jwtSecret))
                    .withAudience(jwtAudience)
                    .withIssuer(jwtIssuer)
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(jwtAudience)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, "Token is not valid or has expired")
            }
        }
    }
}

fun Application.configureHealthCheck() {
    routing {
        get("/health") {
            call.respondText("OK", ContentType.Text.Plain)
        }
    }
}