package com.shibartender.service

import com.shibartender.model.User
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

class UserService {
    private val client = KMongo.createClient()
    private val database = client.getDatabase("user")
    private val userCollection = database.getCollection<User>()

    fun create(user: User): Id<User>? {
        userCollection.insertOne(user)
        return user.id
    }

    fun findAll(): List<User> =
        userCollection.find()
            .toList()

    fun findById(id: String): User? {
        val bsonId: Id<User> = ObjectId(id).toId()
        return userCollection
            .findOne(User::id eq bsonId)
    }

    fun deleteById(id: String): Boolean {
        val deleteResult = userCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}