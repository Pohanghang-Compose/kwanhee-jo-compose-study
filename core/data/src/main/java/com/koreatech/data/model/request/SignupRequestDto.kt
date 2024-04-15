package com.koreatech.data.model.request

import kotlinx.serialization.Serializable


@Serializable
data class SignupRequestDto(
    val username: String,
    val password: String,
    val nickname: String,
)
