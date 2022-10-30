package com.sibartender.dto

import kotlinx.serialization.Serializable

@Serializable
data class CocktailDto(val id: String? = null, val name: String, /* val ingredients: List<IngredientDto>,*/ val method: String, val garnish: String)
