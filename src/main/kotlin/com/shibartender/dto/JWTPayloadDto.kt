package com.shibartender.dto

import kotlinx.serialization.Serializable

@Serializable
data class JWTPayloadDto(val token: String)