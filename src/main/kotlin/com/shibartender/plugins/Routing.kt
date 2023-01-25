package com.shibartender.plugins

import com.shibartender.routes.cocktailRouting
import com.shibartender.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {

    routing {
        userRouting()
        cocktailRouting()
    }
}
