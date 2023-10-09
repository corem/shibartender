package com.shibartender.config

data class Deployment(val port: String)
data class Jwt(val secret: Int, val issuer: String, val validity_ms: String)
data class Mongo(val uri: Int)

data class Config(val deployment: Deployment, val jwt: Jwt, val mongo: Mongo)