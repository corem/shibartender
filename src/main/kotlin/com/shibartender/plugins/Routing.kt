package com.shibartender.plugins

import com.shibartender.routes.cocktailRouting
import com.shibartender.routes.accountRouting
import com.shibartender.routes.randomRouting
import com.shibartender.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*

fun Application.configureRouting() {
    routing {
        accountRouting()
        userRouting()
        cocktailRouting()
        randomRouting()
    }
}
