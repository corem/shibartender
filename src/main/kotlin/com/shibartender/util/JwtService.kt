package com.shibartender.util

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import model.account.User
import java.util.*

interface JwtService {
    fun getVerifier(): JWTVerifier
    fun makeToken(user: User): String
}

class JwtServiceImpl: JwtService {
    private val secret = "4s5fj12lk45n45dx8azx51k4p4m" // TODO: Env
    private val issuer = "shibartender.com" // TODO: Env
    private val validityInMs = 36_000_00 * 10 // 10 hours
    private val algorithm = Algorithm.HMAC512(secret)

    override fun getVerifier(): JWTVerifier {
        return JWT.require(algorithm)
            .withIssuer(issuer)
            .build()
    }

    override fun makeToken(user: User): String = JWT.create()
        .withSubject("Authentication")
        .withIssuer(issuer)
        .withClaim("id", user.id.toString())
        .withExpiresAt(getExpiration())
        .sign(algorithm)

    private fun getExpiration() = Date(System.currentTimeMillis() + validityInMs)

}