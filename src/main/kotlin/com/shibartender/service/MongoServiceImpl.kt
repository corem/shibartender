package com.shibartender.service

import com.mongodb.client.MongoClient
import org.litote.kmongo.KMongo

interface MongoService {
    fun getService(): MongoClient
}

class MongoServiceImpl : MongoService {
    override fun getService(): MongoClient = KMongo.createClient(System.getenv("MONGO_URI"))
}