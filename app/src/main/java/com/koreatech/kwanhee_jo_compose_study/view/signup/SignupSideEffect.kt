package com.koreatech.kwanhee_jo_compose_study.view.signup

sealed class SignupSideEffect {
    data class ShowToast(
        val message: String
    ): SignupSideEffect()
    data class MoveToLoginPage(
        val id: String = "",
        val password: String = "",
        val nickname: String = ""
    ): SignupSideEffect()
}