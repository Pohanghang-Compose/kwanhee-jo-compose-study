package com.koreatech.kwanhee_jo_compose_study.ui

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.koreatech.kwanhee_jo_compose_study.R

sealed class Screen(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList(),
) {
    object Login : Screen(
        route = "login/{id}/{password}/{nickname}",
        navArguments = listOf(
            navArgument("id") { type = NavType.StringType },
            navArgument("password") { type = NavType.StringType },
            navArgument("nickname") { type = NavType.StringType }
        )
    )

    object Signup : Screen(route = "signup") {
        fun createRouteToLogin(id: String, password: String, nickname: String) =
            "login/${id}/${password}/${nickname}"
    }

    sealed class BottomNavItem(val route: String, val icon: Int, val label: String) {
        object Home : BottomNavItem("home", R.drawable.ic_home, "Home")
        object Search : BottomNavItem("search", R.drawable.ic_search, "Search")
        object Setting : BottomNavItem("setting", R.drawable.ic_setting, "Setting")
        companion object {
            val routes = listOf(Home.route, Search.route, Setting.route)
        }
    }
}