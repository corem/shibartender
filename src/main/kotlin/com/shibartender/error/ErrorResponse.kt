package com.shibartender.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String) {
    companion object {
        val INVALID_TOKEN = ErrorResponse("Token is not valid or has expired")
        val NOT_FOUND_RESPONSE = ErrorResponse("Cocktail was not found")
        val BAD_REQUEST_RESPONSE = ErrorResponse("Invalid request")
    }
}