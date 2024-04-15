package com.koreatech.kwanhee_jo_compose_study.composable

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.koreatech.kwanhee_jo_compose_study.data.LoginData
import com.koreatech.kwanhee_jo_compose_study.ui.Screen
import com.koreatech.kwanhee_jo_compose_study.view.composableActivityViewModel
import com.koreatech.kwanhee_jo_compose_study.view.home.HomePage
import com.koreatech.kwanhee_jo_compose_study.view.home.HomeViewModel
import com.koreatech.kwanhee_jo_compose_study.view.navi.serach.SearchPage
import com.koreatech.kwanhee_jo_compose_study.view.navi.serach.SearchViewModel
import com.koreatech.kwanhee_jo_compose_study.view.navi.setting.SettingPage

fun NavGraphBuilder.homeComposable(navController: NavHostController) {
    composable(
        route = Screen.BottomNavItem.Home.route
    ) {
        val context = LocalContext.current
        val homeViewModel: HomeViewModel = composableActivityViewModel()

        val state by homeViewModel.loginData.collectAsState()
        val loginData = it.savedStateHandle.get<LoginData>("loginData")
        if (loginData != null) {
            homeViewModel.updateUserData(loginData)
        }

        HomePage(
            context = context,
            id = state.id,
            password = state.password,
            nickname = state.nickname
        )
    }
    composable(route = Screen.BottomNavItem.Search.route) {
        val context = LocalContext.current
        val searchViewModel: SearchViewModel = hiltViewModel()
        val cats by searchViewModel.cats.collectAsStateWithLifecycle()

        SearchPage(context, cats)
    }
    composable(route = Screen.BottomNavItem.Setting.route) { SettingPage() }
}
