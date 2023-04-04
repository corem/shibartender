package com.shibartender.mappers

import com.shibartender.dto.JWTPayloadDto
import model.account.JWTPayload

fun JWTPayload.toDto(): JWTPayloadDto =
    JWTPayloadDto(
        token = this.token
    )

fun JWTPayloadDto.toJWTPayload(): JWTPayload =
    JWTPayload(
        token = this.token
    )