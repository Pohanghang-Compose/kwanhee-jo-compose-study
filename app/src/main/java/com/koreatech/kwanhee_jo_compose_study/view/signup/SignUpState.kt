package com.koreatech.kwanhee_jo_compose_study.view.signup

sealed class SignUpState {
    object Loading: SignUpState()
    object ErrorId: SignUpState()
    object ErrorPassword: SignUpState()
    object ErrorNickname: SignUpState()
    data class Success(
        val id: String,
        val password: String,
        val nickname: String
    ): SignUpState()
}