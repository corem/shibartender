package com.shibartender.businessservice

import com.shibartender.data.UserRepository
import model.account.User
import org.litote.kmongo.Id

class UserBusinessService(private val userRepository: UserRepository) {
    fun create(user: User): Id<User>? {
        return userRepository.create(user)
    }

    fun findAll(): List<User> {
        return userRepository.findAll()
    }

    fun findById(id: String): User? {
        return userRepository.findById(id)
    }

    fun findByEmail(email: String): User? {
        return userRepository.findByEmail(email)
    }

    fun deleteById(id: String): Boolean {
        return userRepository.deleteById(id)
    }
}