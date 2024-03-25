package com.koreatech.kwanhee_jo_compose_study.view.home

import com.koreatech.kwanhee_jo_compose_study.common.UiStatus

data class HomeState(
    val status: UiStatus = UiStatus.Loading
)