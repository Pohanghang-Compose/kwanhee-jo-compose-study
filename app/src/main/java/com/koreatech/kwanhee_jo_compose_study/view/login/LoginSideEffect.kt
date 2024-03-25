package com.koreatech.kwanhee_jo_compose_study.view.login

sealed class LoginSideEffect {
    object MoveToHomePage: LoginSideEffect()
    object MoveToSignUpPage: LoginSideEffect()
}