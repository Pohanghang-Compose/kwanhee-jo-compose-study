package com.koreatech.kwanhee_jo_compose_study.view.login

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.koreatech.kwanhee_jo_compose_study.Constants.ID
import com.koreatech.kwanhee_jo_compose_study.Constants.NICKNAME
import com.koreatech.kwanhee_jo_compose_study.Constants.PASSWORD
import com.koreatech.kwanhee_jo_compose_study.common.UiStatus
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class LoginViewModel() : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    fun login(inputId: String, receiveId: String, inputPassword: String, receivePassword: String, receiveNickname: String) {
        intent {
            when {
                (inputId.isEmpty() && inputPassword.isEmpty()) -> postSideEffect(LoginSideEffect.ShowToast("로그인 실패"))
                inputId != receiveId -> postSideEffect(LoginSideEffect.ShowToast("아이디 실패"))
                inputPassword != receivePassword -> postSideEffect(LoginSideEffect.ShowToast("비밀번호 실패"))
                else -> {
                    reduce { state.copy(status = UiStatus.Success) }
                    postSideEffect(LoginSideEffect.MoveToHomePage(inputId, inputPassword, receiveNickname))
                }
            }
        }
    }

    fun signUp() {
        intent {
            postSideEffect(LoginSideEffect.MoveToSignUpPage)
        }
    }
}