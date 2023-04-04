package com.shibartender.service

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo

interface MongoServiceInterface {
    fun getService(): MongoClient
}

class MongoService : MongoServiceInterface {
    override fun getService(): MongoClient = KMongo.createClient("mongodb://shibartender-mongo:27017")
}