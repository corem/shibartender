package com.sibartender.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class Cocktail(@BsonId val id: Id<Cocktail>? = null, val name: String, /* val ingredients: List<Ingredient>,*/ val method: String, val garnish: String)