package com.koreatech.kwanhee_jo_compose_study.view.home

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.koreatech.kwanhee_jo_compose_study.Constants.ID
import com.koreatech.kwanhee_jo_compose_study.Constants.NICKNAME
import com.koreatech.kwanhee_jo_compose_study.Constants.PASSWORD
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.viewmodel.container

class HomeViewModel (
    private val savedStateHandle: SavedStateHandle
): ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeState, HomeSideEffect>
        = container(HomeState())

    var id: String
        get() = savedStateHandle.get<String>(ID) ?: ""
        set(value) {
            savedStateHandle[ID] = value
        }

    var password: String
        get() = savedStateHandle.get<String>(PASSWORD) ?: ""
        set(value)  {
            savedStateHandle[PASSWORD] = value
        }

    var nickname: String
        get() = savedStateHandle.get<String>(NICKNAME) ?: ""
        set(value) {
            savedStateHandle[NICKNAME] = value
        }

    fun updateHomeData(id: String, password: String, nickname: String) {
        this.id = id
        this.password = password
        this.nickname = nickname
    }
}