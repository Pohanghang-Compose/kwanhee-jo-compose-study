package com.koreatech.kwanhee_jo_compose_study

sealed class Screen(val route: String) {
    object Login: Screen(route = "login")
    object SignUp: Screen(route = "sign_up")
    object Home: Screen(route = "home")
}