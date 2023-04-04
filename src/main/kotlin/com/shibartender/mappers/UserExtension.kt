package com.shibartender.mappers

import com.shibartender.dto.UserCreationDto
import com.shibartender.dto.UserDto
import com.shibartender.dto.UserLoginDto
import model.account.User
import org.mindrot.jbcrypt.BCrypt

fun User.toDto(): UserDto =
    UserDto(
        id = this.id.toString(),
        email = this.email,
        username = this.username,
        password = this.password,
    )

fun UserDto.toUser(): User =
    User(
        email = this.email,
        username = this.username,
        password = this.password
    )

fun UserCreationDto.toUser(): User =
    User(
        email = this.email,
        username = this.username,
        password = BCrypt.hashpw(this.password, BCrypt.gensalt())
    )