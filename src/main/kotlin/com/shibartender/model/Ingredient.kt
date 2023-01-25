package com.shibartender.model

import kotlinx.serialization.Serializable

@Serializable
data class Ingredient(val name: String, val unit: String, val quantity: Int)

