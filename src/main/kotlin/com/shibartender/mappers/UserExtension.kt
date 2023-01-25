package com.shibartender.mappers

import com.shibartender.dto.UserDto
import com.shibartender.model.User

fun User.toDto(): UserDto =
    UserDto(
        id = this.id.toString(),
        username = this.username,
        password = this.password,
    )

fun UserDto.toUser(): User =
    User(
        username = this.username,
        password = this.password
    )