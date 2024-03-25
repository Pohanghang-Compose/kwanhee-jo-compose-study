package com.koreatech.kwanhee_jo_compose_study.view.signup

sealed class SignUpSideEffect {
    data class BackToLoginPage(
        val id: String = "",
        val password: String = "",
        val nickname: String = ""
    ): SignUpSideEffect()
}