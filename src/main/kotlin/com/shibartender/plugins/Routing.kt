package com.shibartender.plugins

import com.shibartender.routes.cocktailRouting
import com.shibartender.routes.accountRouting
import com.shibartender.routes.randomRouting
import com.shibartender.routes.userRouting
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.http.content.*
import java.io.File

fun Application.configureRouting() {
    routing {
        staticFiles("/favicon", File("favicon.ico"))
        accountRouting()
        userRouting()
        cocktailRouting()
        randomRouting()
    }
}
