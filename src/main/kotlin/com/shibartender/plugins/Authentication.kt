package com.shibartender.plugins

import com.shibartender.error.ErrorResponse
import com.shibartender.util.JwtService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.response.*
import org.koin.ktor.ext.inject

fun Application.configureAuthentication() {
    val jwtService by inject<JwtService>()

    install(Authentication) {
        jwt("auth-jwt") {
            verifier(jwtService.getVerifier())
            realm = "shibartender.com"
            validate { credential ->
                if (credential.payload.getClaim("username").asString() != "") {
                    JWTPrincipal(credential.payload)
                } else {
                    null
                }
            }
            challenge { defaultScheme, realm ->
                call.respond(HttpStatusCode.Unauthorized, ErrorResponse.INVALID_TOKEN)
            }
        }
    }
}