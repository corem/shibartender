package com.shibartender.plugins

import com.shibartender.routes.customerRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        customerRouting()
    }
}
