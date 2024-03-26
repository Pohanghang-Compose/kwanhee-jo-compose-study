package com.koreatech.kwanhee_jo_compose_study.view.login

sealed class LoginSideEffect {
    data class MoveToHomePage(
        val id: String,
        val password: String,
        val nickname: String
    ): LoginSideEffect()
    object MoveToSignUpPage: LoginSideEffect()
    data class ShowToast(
        val message: String
    ): LoginSideEffect()
}