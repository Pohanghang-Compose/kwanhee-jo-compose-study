package com.koreatech.kwanhee_jo_compose_study.view.signup

import androidx.lifecycle.ViewModel
import com.koreatech.kwanhee_jo_compose_study.common.UiStatus
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignupViewModel : ContainerHost<SignupState, SignupSideEffect>, ViewModel() {
    override val container: Container<SignupState, SignupSideEffect> = container(SignupState())

    fun signup(id: String, password: String, nickname: String) {
        intent {
            when {
                !(id.length in 6..10) -> postSideEffect(SignupSideEffect.ShowToast("아이디 실패"))
                !(password.length in 8..10) -> postSideEffect(SignupSideEffect.ShowToast("비밀번호 실패"))
                (nickname.isEmpty()) -> postSideEffect(SignupSideEffect.ShowToast("닉네임 실패"))
                else -> {
                    reduce { state.copy(status = UiStatus.Success) }
                    postSideEffect(SignupSideEffect.MoveToLoginPage(id, password, nickname))
                }
            }
        }
    }

}