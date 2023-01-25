package com.shibartender.model

import kotlinx.serialization.Serializable
import org.bson.codecs.pojo.annotations.BsonId
import org.litote.kmongo.Id

@Serializable
data class User(@BsonId val id: Id<User>? = null, val username: String, val password: String)