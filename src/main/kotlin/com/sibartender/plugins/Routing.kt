package com.sibartender.plugins

import com.sibartender.routes.customerRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        customerRouting()
    }
}
