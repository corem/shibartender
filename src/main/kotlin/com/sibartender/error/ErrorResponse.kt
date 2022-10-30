package com.sibartender.error

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(val message: String) {
    companion object {
        val NOT_FOUND_RESPONSE = ErrorResponse("Cocktail was not found")
        val BAD_REQUEST_RESPONSE = ErrorResponse("Invalid request")
    }
}