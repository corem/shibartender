package com.shibartender.data

import com.mongodb.client.MongoClient
import model.drink.Cocktail
import org.bson.types.ObjectId
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

interface CocktailRepository {
    fun create(cocktail: Cocktail): Id<Cocktail>?
    fun findAll(): List<Cocktail>
    fun findById(id: String): Cocktail?
    fun findByName(name: String): List<Cocktail>
    fun deleteById(id: String): Boolean
}

class CocktailRepositoryImpl(client: MongoClient) : CocktailRepository {
//    private val client: MongoClient by inject()
    private val database = client.getDatabase("cocktail")
    private val cocktailCollection = database.getCollection<Cocktail>()

    override fun create(cocktail: Cocktail): Id<Cocktail>? {
        cocktailCollection.insertOne(cocktail)
        return cocktail.id
    }

    override fun findAll(): List<Cocktail> =
        cocktailCollection.find()
            .toList()

    override fun findById(id: String): Cocktail? {
        val bsonId: Id<Cocktail> = ObjectId(id).toId()
        return cocktailCollection
            .findOne(Cocktail::id eq bsonId)
    }

    override fun findByName(name: String): List<Cocktail> {
        val caseSensitiveTypeSafeFilter = Cocktail::name regex name
        return cocktailCollection.find(caseSensitiveTypeSafeFilter)
            .toList()
    }

    override fun deleteById(id: String): Boolean {
        val deleteResult = cocktailCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}