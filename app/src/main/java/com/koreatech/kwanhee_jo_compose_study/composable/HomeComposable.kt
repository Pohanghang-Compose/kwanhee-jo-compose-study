package com.koreatech.kwanhee_jo_compose_study.composable

import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.koreatech.kwanhee_jo_compose_study.Constants
import com.koreatech.kwanhee_jo_compose_study.Screen
import com.koreatech.kwanhee_jo_compose_study.view.composableActivityViewModel
import com.koreatech.kwanhee_jo_compose_study.view.home.HomePage
import com.koreatech.kwanhee_jo_compose_study.view.home.HomeViewModel
import com.koreatech.kwanhee_jo_compose_study.view.navi.serach.SearchPage
import com.koreatech.kwanhee_jo_compose_study.view.navi.setting.SettingPage

fun NavGraphBuilder.homeComposable(navController: NavHostController) {
    composable(
        route = Screen.BottomNavItem.Home.route
    ) {
        val context = LocalContext.current
        val homeViewModel: HomeViewModel = composableActivityViewModel()
        // val homeState by homeViewModel.collectAsState()

        it.savedStateHandle.apply {
            val id = get<String>(Constants.ID)
            val password = get<String>(Constants.PASSWORD)
            val nickname = get<String>(Constants.NICKNAME)
            if (id != null || password != null || nickname != null) {
                homeViewModel.updateUserData(
                    id ?: "", password ?: "", nickname ?: ""
                )
                HomePage(
                    context = context,
                    id = id ?: "",
                    password = password ?: "",
                    nickname = nickname ?: ""
                )
            } else {
                HomePage(
                    context = context,
                    id = homeViewModel.id,
                    password = homeViewModel.password,
                    nickname = homeViewModel.nickname
                )
            }
        }

    }
    composable(route = Screen.BottomNavItem.Search.route) {
        SearchPage()
    }
    composable(route = Screen.BottomNavItem.Setting.route) {
        SettingPage()
    }
}
