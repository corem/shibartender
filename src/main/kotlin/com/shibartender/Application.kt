package com.shibartender

import com.shibartender.plugins.configureAuthentication
import com.shibartender.plugins.configureKoin
import com.shibartender.plugins.configureRouting
import com.shibartender.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
//    val port = environment.config.propertyOrNull("ktor.deployment.port")?.getString()
//    val jwtSecret = environment.config.propertyOrNull("ktor.jwt.secret")?.getString()
//    val jwtIssuer = environment.config.propertyOrNull("ktor.jwt.issuer")?.getString()
//    val jwtValidityToken = environment.config.propertyOrNull("ktor.jwt.validity_ms")?.getString()
//    val mongoUri = environment.config.propertyOrNull("ktor.mongo.uri")?.getString()

    configureKoin()
    configureAuthentication()
    configureRouting()
    configureSerialization()
}