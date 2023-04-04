package com.shibartender.util

import com.auth0.jwt.*
import com.auth0.jwt.algorithms.*
import model.account.User
import org.mindrot.jbcrypt.BCrypt
import java.util.*

object Cryptography {
    fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}