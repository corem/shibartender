package com.shibartender

import com.shibartender.plugins.configureAuthentication
import com.shibartender.plugins.configureKoin
import com.shibartender.plugins.configureRouting
import com.shibartender.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    configureKoin()
    configureAuthentication()
    configureRouting()
    configureSerialization()
}