package com.shibartender.routes

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.randomRouting() {
    route("/olivier") {
        get {
            call.respond(HttpStatusCode.OK, "tg Brieux")
        }
    }
}