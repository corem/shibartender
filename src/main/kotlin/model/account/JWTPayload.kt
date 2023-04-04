package model.account

import kotlinx.serialization.Serializable

@Serializable
data class JWTPayload(val token: String)