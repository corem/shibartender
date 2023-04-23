package com.shibartender.routes

import io.ktor.http.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlin.test.*
import io.ktor.server.testing.*

class AccountRoutesTest {
    @Test
    fun testRoot() = testApplication {
        client.get("/account").apply {
            assertEquals(HttpStatusCode.Accepted, status)
            assertEquals("Bonjour-Hi World", bodyAsText())
        }
    }
}