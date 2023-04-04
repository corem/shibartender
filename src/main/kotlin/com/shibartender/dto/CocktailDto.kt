package com.shibartender.dto

import model.drink.Ingredient
import kotlinx.serialization.Serializable

@Serializable
data class CocktailDto(val id: String? = null, val name: String, val ingredients: List<Ingredient>, val method: String, val garnish: String)
