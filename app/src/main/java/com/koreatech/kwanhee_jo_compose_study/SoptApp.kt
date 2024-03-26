package com.koreatech.kwanhee_jo_compose_study

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.koreatech.kwanhee_jo_compose_study.view.SoptNavHost

@Composable
fun SoptApp() {
    val navController = rememberNavController()
    SoptNavHost(navController = navController)
}