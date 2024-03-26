package com.koreatech.kwanhee_jo_compose_study.view

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import com.koreatech.kwanhee_jo_compose_study.Screen
import com.koreatech.kwanhee_jo_compose_study.composable.accountComposable
import com.koreatech.kwanhee_jo_compose_study.composable.homeComposable
import com.koreatech.kwanhee_jo_compose_study.view.navi.BottomNavigationBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SoptNavHost(
    navController: NavHostController,
) {
    Scaffold(
        bottomBar = {
            if (currentRoute(navController = navController) in Screen.BottomNavItem.routes) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            accountComposable(navController = navController)
            homeComposable(navController = navController)
        }
    }
}

@Composable
fun currentRoute(navController: NavHostController): String? {
    val backStackEntry = navController.currentBackStackEntryAsState()
    return backStackEntry.value?.destination?.route
}

@Composable
fun getActivity() = LocalContext.current as ComponentActivity

@Composable
inline fun <reified VM : ViewModel> composableActivityViewModel(
    key: String? = null,
    factory: ViewModelProvider.Factory? = null,
): VM = viewModel(
    VM::class.java,
    getActivity(), key, factory
)