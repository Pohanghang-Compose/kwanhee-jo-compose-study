package com.koreatech.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

data class CatResponseDto(
    @SerialName("id")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("width")
    val width: Int,
    @SerialName("height")
    val height: Int,
)
