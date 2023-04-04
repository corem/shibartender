package com.shibartender.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserCreationDto(val id: String? = null, val email: String, val username: String, val password: String)
