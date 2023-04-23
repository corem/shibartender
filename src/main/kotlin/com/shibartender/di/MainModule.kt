package com.shibartender

import com.shibartender.businessservice.AccountBusinessService
import com.shibartender.businessservice.CocktailBusinessService
import com.shibartender.businessservice.UserBusinessService
import com.shibartender.data.CocktailRepository
import com.shibartender.data.CocktailRepositoryImpl
import com.shibartender.data.UserRepository
import com.shibartender.data.UserRepositoryImpl
import com.shibartender.service.MongoService
import com.shibartender.service.MongoServiceImpl
import com.shibartender.util.CryptographyService
import com.shibartender.util.CryptographyServiceImpl
import com.shibartender.util.JwtService
import com.shibartender.util.JwtServiceImpl
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val mainModule = module {
    // Utils
    singleOf(::JwtServiceImpl) { bind<JwtService>() }
    singleOf(::CryptographyServiceImpl) { bind<CryptographyService>() }

    // Repository
    singleOf(::MongoServiceImpl) { bind<MongoService>() }
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    singleOf(::CocktailRepositoryImpl) { bind<CocktailRepository>() }

    // Services
    singleOf(::UserBusinessService)
    singleOf(::AccountBusinessService)
    singleOf(::CocktailBusinessService)
}

