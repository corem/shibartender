package com.shibartender.util

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import model.account.User
import java.util.*

object JwtConfig {

    private const val secret = "4s5fj12lk45n45dx8azx51k4p4m"
    private const val issuer = "shibartender.com"
    private const val validityInMs = 36_000_00 * 10 // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    val verifier: JWTVerifier = JWT
        .require(algorithm)
        .withIssuer(issuer)
        .build()

    fun makeToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id.toString())
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}