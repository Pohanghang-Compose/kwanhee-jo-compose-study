package com.koreatech.kwanhee_jo_compose_study.common

sealed class UiStatus {
    object Loading: UiStatus()
    object Success: UiStatus()
    data class Failed(val message: String = ""): UiStatus()
}