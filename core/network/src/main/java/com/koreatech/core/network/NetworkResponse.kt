package com.koreatech.core.network

import kotlinx.serialization.Serializable

@Serializable
data class NetworkResponse<T>(
    val data: T
)
