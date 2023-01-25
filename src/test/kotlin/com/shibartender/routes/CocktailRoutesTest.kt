package com.shibartender.routes

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class  CocktailRoutesTest{
    @Test
    fun testGetCocktails() = testApplication {
        val response = client.get("/cocktail")
        assertEquals(
            """[{"id":"636ada20f5c6b23701c174cf","name":"Boulevardier","ingredients":[{"name":"Bourbon","unit":"cl","quantity":50}],"method":"Pour all ingredients directly into a chilled old fashioned glass filled with ice, Stir gently.","garnish":"Garnish with a half orange slice."}]""",
            response.bodyAsText()
        )
        assertEquals(HttpStatusCode.OK, response.status)
    }
}