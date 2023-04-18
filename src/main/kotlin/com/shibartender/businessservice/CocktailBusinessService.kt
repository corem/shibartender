package com.shibartender.businessservice

import com.shibartender.data.CocktailRepository
import model.drink.Cocktail
import org.litote.kmongo.Id

class CocktailBusinessService(private val cocktailRepository: CocktailRepository) {

    fun create(cocktail: Cocktail): Id<Cocktail>? {
        return cocktailRepository.create(cocktail)
    }

    fun findAll(): List<Cocktail> {
        return cocktailRepository.findAll()
    }

    fun findById(id: String): Cocktail? {
       return cocktailRepository.findById(id)
    }

    fun findByName(name: String): List<Cocktail> {
        return cocktailRepository.findByName(name)
    }

    fun deleteById(id: String): Boolean {
        return cocktailRepository.deleteById(id)
    }
}