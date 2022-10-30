package com.shibartender.routes

import com.shibartender.dto.CocktailDto
import com.shibartender.error.ErrorResponse
import com.shibartender.model.Cocktail
import com.shibartender.service.CocktailService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import toCocktail
import toDto

val service = CocktailService()

fun Route.customerRouting() {
    route("/cocktail") {
        get {
            val cocktailList =
                service.findAll()
                    .map(Cocktail::toDto)
            call.respond(cocktailList)
        }

        get("{id?}") {
            val id = call.parameters["id"].toString()
            service.findById(id)
                ?.let { foundCocktail -> call.respond(foundCocktail.toDto()) }
                ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
        }

        post {
            val request = call.receive<CocktailDto>()
            val cocktail = request.toCocktail()
            service.create(cocktail)
                ?.let { userId ->
                    call.response.headers.append("My-User-Id-Header", userId.toString())
                    call.respond(HttpStatusCode.Created)
                } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
        }

        delete("{id?}") {
            val id = call.parameters["id"].toString()
            val deletedSuccessfully = service.deleteById(id)
            if (deletedSuccessfully) {
                call.respond(HttpStatusCode.NoContent)
            } else {
                call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
            }
        }
    }
}