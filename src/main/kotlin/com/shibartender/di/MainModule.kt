package com.shibartender

import org.koin.dsl.module
import org.litote.kmongo.KMongo

val mainModule = module(createdAtStart = true) {
    factory { KMongo.createClient("mongodb://shibartender-mongo:27017") }
}

