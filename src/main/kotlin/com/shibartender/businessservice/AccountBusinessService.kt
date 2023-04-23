package com.shibartender.businessservice

import com.shibartender.data.UserRepository
import com.shibartender.util.JwtService
import model.account.JWTPayload
import model.account.User
import org.litote.kmongo.Id
import org.mindrot.jbcrypt.BCrypt

class AccountBusinessService(private val userRepository: UserRepository,
                             private val jwtService: JwtService) {

    fun create(user: User): Id<User>? {
        userRepository.findByEmail(user.email)
            ?.let { _ -> return null }
            ?: return userRepository.create(user)
    }

    fun login(email: String, password: String): JWTPayload? {
        userRepository.findByEmail(email)
            ?.let { user ->
                return if (BCrypt.checkpw(password, user.password)) JWTPayload(jwtService.makeToken(user)) else null
            } ?: return null
    }
}