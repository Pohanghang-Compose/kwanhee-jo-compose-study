package com.koreatech.kwanhee_jo_compose_study.view.signup

import androidx.lifecycle.ViewModel
import com.koreatech.kwanhee_jo_compose_study.common.UiStatus
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container

class SignUpViewModel : ContainerHost<SignUpState, SignUpSideEffect>, ViewModel() {
    override val container: Container<SignUpState, SignUpSideEffect> = container(SignUpState())

    fun register(id: String, password: String, nickname: String) {
        intent {
            when {
                !(id.length in 6..10) -> reduce { state.copy(status = UiStatus.Failed("Failed Id")) }
                !(password.length in 8..10) -> reduce { state.copy(status = UiStatus.Failed("Failed Password")) }
                (nickname.isEmpty()) -> reduce { state.copy(status = UiStatus.Failed("Failed Nickname")) }
                else -> {
                    reduce { state.copy(status = UiStatus.Success) }
                    postSideEffect(SignUpSideEffect.BackToLoginPage(id, password, nickname))
                }
            }
        }
    }

}