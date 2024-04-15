package com.koreatech.kwanhee_jo_compose_study.view.navi

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import com.koreatech.kwanhee_jo_compose_study.ui.Screen
import com.koreatech.kwanhee_jo_compose_study.view.currentRoute

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.BottomNavItem.Search,
        Screen.BottomNavItem.Home,
        Screen.BottomNavItem.Setting
    )
    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                selected = currentRoute(navController = navController) == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId)
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                label = { Text(text = item.label) }
            )
        }
    }
}