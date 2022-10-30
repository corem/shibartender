package com.shibartender.dto

import kotlinx.serialization.Serializable

@Serializable
data class IngredientDto(val name: String, val unit: String, val quantity: Int)
