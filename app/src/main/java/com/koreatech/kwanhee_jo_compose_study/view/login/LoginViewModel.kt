package com.koreatech.kwanhee_jo_compose_study.view.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.koreatech.core.network.repository.PohahangRepository
import com.koreatech.data.model.request.LoginRequestDto
import com.koreatech.data.model.response.CatResponseDto
import com.koreatech.kwanhee_jo_compose_study.common.UiStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
) : ContainerHost<LoginState, LoginSideEffect>, ViewModel() {
    override val container: Container<LoginState, LoginSideEffect> = container(LoginState())



    fun login(
        inputId: String,
        receiveId: String,
        inputPassword: String,
        receivePassword: String,
        receiveNickname: String,
    ) {
        intent {
            when {
                (inputId.isEmpty() && inputPassword.isEmpty()) -> postSideEffect(
                    LoginSideEffect.ShowToast(
                        "로그인 실패"
                    )
                )

                inputId != receiveId -> postSideEffect(LoginSideEffect.ShowToast("아이디 실패"))
                inputPassword != receivePassword -> postSideEffect(LoginSideEffect.ShowToast("비밀번호 실패"))
                else -> {
                    reduce { state.copy(status = UiStatus.Success) }
                    postSideEffect(
                        LoginSideEffect.MoveToHomePage(
                            inputId,
                            inputPassword,
                            receiveNickname
                        )
                    )
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