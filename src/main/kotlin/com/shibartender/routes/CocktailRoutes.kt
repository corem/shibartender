package com.shibartender.routes

import com.shibartender.dto.CocktailDto
import com.shibartender.error.ErrorResponse
import com.shibartender.model.Cocktail
import com.shibartender.service.CocktailService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import toCocktail
import toDto

val cocktailService = CocktailService()

fun Route.cocktailRouting() {
//    authenticate("auth-jwt") {
        route("/cocktail") {
            get {
                val cocktailList =
                    cocktailService.findAll()
                        .map(Cocktail::toDto)
                call.respond(cocktailList)
            }

            get("{id?}") {
                val id = call.parameters["id"].toString()
                cocktailService.findById(id)
                    ?.let { foundCocktail -> call.respond(foundCocktail.toDto()) }
                    ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
            }

            post {
                val request = call.receive<CocktailDto>()
                val cocktail = request.toCocktail()
                cocktailService.create(cocktail)
                    ?.let { cocktailId ->
                        call.response.headers.append("Cocktail-Id-Header", cocktailId.toString())
                        call.respond(HttpStatusCode.Created)
                    } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            }

            delete("{id?}") {
                val id = call.parameters["id"].toString()
                val deletedSuccessfully = cocktailService.deleteById(id)
                if (deletedSuccessfully) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
                }
            }
        }
//   }
}