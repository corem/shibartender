package com.shibartender.service

import com.shibartender.model.Cocktail
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class CocktailService {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("cocktail")
    private val cocktailCollection = database.getCollection<Cocktail>()

    fun create(cocktail: Cocktail): Id<Cocktail>? {
        cocktailCollection.insertOne(cocktail)
        return cocktail.id
    }

    fun findAll(): List<Cocktail> =
        cocktailCollection.find()
            .toList()

    fun findById(id: String): Cocktail? {
        val bsonId: Id<Cocktail> = ObjectId(id).toId()
        return cocktailCollection
            .findOne(Cocktail::id eq bsonId)
    }

    fun findByName(name: String): List<Cocktail> {
        val caseSensitiveTypeSafeFilter = Cocktail::name regex name
        return cocktailCollection.find(caseSensitiveTypeSafeFilter)
            .toList()
    }

    fun deleteById(id: String): Boolean {
        val deleteResult = cocktailCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}