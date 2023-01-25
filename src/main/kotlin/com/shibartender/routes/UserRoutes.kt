package com.shibartender.routes

import com.shibartender.dto.UserDto
import com.shibartender.error.ErrorResponse
import com.shibartender.mappers.toDto
import com.shibartender.mappers.toUser
import com.shibartender.model.User
import com.shibartender.service.UserService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val userService = UserService()

fun Route.userRouting() {
//    authenticate("auth-jwt") {
        route("/user") {
            get {
                val userList =
                    userService.findAll()
                        .map(User::toDto)
                call.respond(userList)
            }

            post {
                val request = call.receive<UserDto>()
                val user = request.toUser()
                userService.create(user)
                    ?.let { userId ->
                        call.response.headers.append("User-Id-Header", userId.toString())
                        call.respond(HttpStatusCode.Created)
                    } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            }
        }
//    }
}