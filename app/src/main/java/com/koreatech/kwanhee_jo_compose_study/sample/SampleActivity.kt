package com.koreatech.kwanhee_jo_compose_study.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.koreatech.kwanhee_jo_compose_study.ui.theme.KwanheeTheme

class SampleActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KwanheeTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController, startDestination = Route.One.route
                ) {
                    composable(route = Route.One.route) {
                        Button(onClick = {
                            navController.navigate(Route.Two.route) {
                                popUpTo(popUpToId) {
                                    inclusive = true
                                }
                            }
                        }) {

                        }
                    }
                    composable(route = Route.Two.route) {
                        Text(text = "two")
                    }
                }
            }
        }
    }
}

sealed class Route(val route: String) {
    object One : Route(route = "One")
    object Two : Route(route = "Two")
}
