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
    savedStateHandle: SavedStateHandle
): ContainerHost<HomeState, HomeSideEffect>, ViewModel() {
    override val container: Container<HomeState, HomeSideEffect>
        = container(HomeState())

    var id : String  = ""
    var password: String = ""
    var nickname: String = ""

    fun updateUserData(id: String, password: String, nickname: String) {
        this.id = id
        this.password = password
        this.nickname = nickname
    }
}