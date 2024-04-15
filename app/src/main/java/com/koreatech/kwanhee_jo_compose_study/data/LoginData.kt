package com.koreatech.kwanhee_jo_compose_study.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginData(
    val id: String = "",
    val password: String ="",
    val nickname: String =""
): Parcelable

