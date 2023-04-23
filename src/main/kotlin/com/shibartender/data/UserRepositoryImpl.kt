package com.shibartender.data

import com.shibartender.service.MongoService
import model.account.User
import org.bson.types.ObjectId
import org.litote.kmongo.*
import org.litote.kmongo.id.toId

interface UserRepository {
    fun create(user: User): Id<User>?
    fun findAll(): List<User>
    fun findById(id: String): User?
    fun findByEmail(email: String): User?
    fun deleteById(id: String): Boolean
}

class UserRepositoryImpl(private val client: MongoService) : UserRepository {
    private val database = client.getService().getDatabase("user")
    private val userCollection = database.getCollection<User>()

    override fun create(user: User): Id<User>? {
        userCollection.insertOne(user)
        return user.id
    }

    override fun findAll(): List<User> =
        userCollection.find()
            .toList()

    override fun findById(id: String): User? {
        val bsonId: Id<User> = ObjectId(id).toId()
        return userCollection
            .findOne(User::id eq bsonId)
    }

    override fun findByEmail(email: String): User? {
        return userCollection.findOne(User::email eq email)
    }

    override fun deleteById(id: String): Boolean {
        val deleteResult = userCollection.deleteOneById(ObjectId(id))
        return deleteResult.deletedCount == 1L
    }
}