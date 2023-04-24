package com.shibartender

import com.shibartender.plugins.configureAuthentication
import com.shibartender.plugins.configureKoin
import com.shibartender.plugins.configureRouting
import com.shibartender.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    println(environment.config.propertyOrNull("ktor.deployment.port")?.getString() ?: "Not found")
    println(environment.config.propertyOrNull("ktor.jwt.secret")?.getString() ?: "Not found")
    println(environment.config.propertyOrNull("ktor.jwt.issuer")?.getString() ?: "Not found")
    println(environment.config.propertyOrNull("ktor.jwt.validity_ms")?.getString() ?: "Not found")
    println(environment.config.propertyOrNull("ktor.mongo.uri")?.getString() ?: "Not found")

    configureKoin()
    configureAuthentication()
    configureRouting()
    configureSerialization()
}