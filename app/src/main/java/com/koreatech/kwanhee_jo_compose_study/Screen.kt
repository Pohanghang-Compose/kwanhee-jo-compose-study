package com.koreatech.kwanhee_jo_compose_study

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String) {
    object Login: Screen(route = "login")
    object SignUp: Screen(route = "sign_up")
    sealed class BottomNavItem (val route: String, val icon: ImageVector, val label: String) {
        object Home: BottomNavItem("home", Icons.Default.Home, "Home")
        object Search: BottomNavItem("search", Icons.Default.Search, "Search")
        object Setting: BottomNavItem("setting", Icons.Default.Settings, "Setting")
    }
}