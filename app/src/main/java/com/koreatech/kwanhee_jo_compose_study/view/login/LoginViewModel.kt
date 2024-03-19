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

class LoginViewModel(
    private val savedStateHandle: SavedStateHandle,
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())

    var id: String
        get() = savedStateHandle.get<String>(ID) ?: ""
        set(value) {
            savedStateHandle[ID] = value
        }

    var password: String
        get() = savedStateHandle.get<String>(PASSWORD) ?: ""
        set(value) {
            savedStateHandle[PASSWORD] = value
        }

    var nickname: String
        get() = savedStateHandle.get<String>(NICKNAME) ?: ""
        set(value) {
            savedStateHandle[NICKNAME] = value
        }

    init {
        Log.e("aaa", "viewmodel savedStateHandle.get<String>(ID) : ${savedStateHandle.get<String>(ID)}")
    }

    fun updateLoginSavedStateHandle(id: String, password: String, nickname: String) {
        this.id = id
        this.password = password
        this.nickname = nickname
    }


    fun login(inputId: String, inputPassword: String) {
        intent {
            when {
                (inputId.isEmpty() && inputPassword.isEmpty()) -> { state.copy(status = UiStatus.Failed("input field"))}
                inputId != id -> reduce { state.copy(status = UiStatus.Failed("Failed Id")) }
                inputPassword != password -> reduce { state.copy(status = UiStatus.Failed("Failed Password")) }
                else -> {
                    reduce { state.copy(status = UiStatus.Success) }
                    postSideEffect(LoginSideEffect.MoveToHomePage)
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