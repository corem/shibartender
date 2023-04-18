package com.shibartender

import com.shibartender.businessservice.AccountBusinessService
import com.shibartender.businessservice.CocktailBusinessService
import com.shibartender.businessservice.UserBusinessService
import com.shibartender.data.CocktailRepository
import com.shibartender.data.CocktailRepositoryImpl
import com.shibartender.data.UserRepository
import com.shibartender.data.UserRepositoryImpl
import com.shibartender.util.CryptographyService
import com.shibartender.util.CryptographyServiceImpl
import com.shibartender.util.JwtService
import com.shibartender.util.JwtServiceImpl
import org.koin.dsl.module
import org.litote.kmongo.KMongo

val mainModule = module(createdAtStart = true) {
    single<JwtService> { JwtServiceImpl() }
    single<CryptographyService> { CryptographyServiceImpl() }
//    factory { KMongo.createClient(System.getenv("MONGO_URI")) } // TODO: Env
    factory { KMongo.createClient("mongodb://shibartender-mongo:27017") } // TODO: Env

    single<UserRepository> { UserRepositoryImpl(get()) }
    single<CocktailRepository> { CocktailRepositoryImpl(get()) }

    single { UserBusinessService(get()) }
    single { AccountBusinessService(get(), get()) }
    single { CocktailBusinessService(get()) }
}

