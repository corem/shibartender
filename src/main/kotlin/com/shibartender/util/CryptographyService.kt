package com.shibartender.util

import org.mindrot.jbcrypt.BCrypt

interface CryptographyService {
    fun hashPassword(password: String): String
}

class CryptographyServiceImpl : CryptographyService {
    override fun hashPassword(password: String): String {
        return BCrypt.hashpw(password, BCrypt.gensalt())
    }
}