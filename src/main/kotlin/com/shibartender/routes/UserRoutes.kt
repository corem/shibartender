package com.shibartender.routes

import com.shibartender.dto.UserDto
import com.shibartender.error.ErrorResponse
import com.shibartender.mappers.toDto
import com.shibartender.mappers.toUser
import model.account.User
import com.shibartender.businessservice.UserBusinessService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject


fun Route.userRouting() {
    val userBusinessService by inject<UserBusinessService>()

    authenticate("auth-jwt") {
        route("/user") {
            get {
                val userList =
                    userBusinessService.findAll()
                        .map(User::toDto)
                call.respond(userList)
            }

            get("{id?}") {
                val id = call.parameters["id"].toString()
                userBusinessService.findById(id)
                    ?.let { foundUser -> call.respond(foundUser.toDto()) }
                    ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
            }

            post {
                val request = call.receive<UserDto>()
                val user = request.toUser()
                userBusinessService.create(user)
                    ?.let { userId ->
                        call.response.headers.append("User-Id-Header", userId.toString())
                        call.respond(HttpStatusCode.Created)
                    } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            }
        }
    }
}