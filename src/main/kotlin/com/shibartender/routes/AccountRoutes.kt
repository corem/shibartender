package com.shibartender.routes

import com.shibartender.dto.UserCreationDto
import com.shibartender.dto.UserLoginDto
import com.shibartender.error.ErrorResponse
import com.shibartender.mappers.toDto
import com.shibartender.mappers.toUser
import com.shibartender.businessservice.AccountBusinessService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.accountRouting() {
    val accountBusinessService by inject<AccountBusinessService>()

    route("/account") {
        get {
            call.respond(HttpStatusCode.Accepted, "Bonjour-Hi World")
        }

        post("/create") {
            val request = call.receive<UserCreationDto>()
            val user = request.toUser()
            accountBusinessService.create(user)
                ?.let { userId ->
                    call.response.headers.append("User-Id-Header", userId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        post("/login") {
            val user = call.receive<UserLoginDto>()
            accountBusinessService.login(user.email, user.password)
                ?.let { jwtPayload ->
                    call.respond(jwtPayload.toDto())
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }
    }
}