package com.shibartender.routes

import com.shibartender.dto.CocktailDto
import com.shibartender.error.ErrorResponse
import model.drink.Cocktail
import com.shibartender.businessservice.CocktailBusinessService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import toCocktail
import toDto



fun Route.cocktailRouting() {
    val cocktailBusinessService by inject<CocktailBusinessService>()

    authenticate("auth-jwt") {
        route("/cocktail") {
            get {
                val cocktailList =
                    cocktailBusinessService.findAll()
                        .map(Cocktail::toDto)
                call.respond(cocktailList)
            }

            get("{id?}") {
                val id = call.parameters["id"].toString()
                cocktailBusinessService.findById(id)
                    ?.let { foundCocktail -> call.respond(foundCocktail.toDto()) }
                    ?: call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
            }

            post {
                val request = call.receive<CocktailDto>()
                val cocktail = request.toCocktail()
                cocktailBusinessService.create(cocktail)
                    ?.let { cocktailId ->
                        call.response.headers.append("Cocktail-Id-Header", cocktailId.toString())
                        call.respond(HttpStatusCode.Created)
                    } ?: call.respond(HttpStatusCode.BadRequest, ErrorResponse.BAD_REQUEST_RESPONSE)
            }

            delete("{id?}") {
                val id = call.parameters["id"].toString()
                val deletedSuccessfully = cocktailBusinessService.deleteById(id)
                if (deletedSuccessfully) {
                    call.respond(HttpStatusCode.NoContent)
                } else {
                    call.respond(HttpStatusCode.NotFound, ErrorResponse.NOT_FOUND_RESPONSE)
                }
            }
        }
   }
}