package com.koreatech.kwanhee_jo_compose_study

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.koreatech.kwanhee_jo_compose_study.Constants.ID
import com.koreatech.kwanhee_jo_compose_study.Constants.NICKNAME
import com.koreatech.kwanhee_jo_compose_study.Constants.PASSWORD
import com.koreatech.kwanhee_jo_compose_study.ui.theme.KwanheeTheme
import com.koreatech.kwanhee_jo_compose_study.view.home.HomePage
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginPage
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignUpPage
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignUpState
import kotlinx.coroutines.flow.MutableStateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KwanheeTheme {
                Box(modifier = Modifier.fillMaxSize()) {
                    val navController = rememberNavController()
                    val context = LocalContext.current
                    NavHost(navController = navController, startDestination = Screen.Login.route) {
                        addLoginPage(navController = navController, context)
                        addSignUpPage(navController = navController)
                        addHomePage(navController = navController)
                    }
                }
            }
        }
    }
}

private fun NavGraphBuilder.addLoginPage(navController: NavController, context: Context) {
    composable(route = Screen.Login.route) { backStackEntry ->
        val userId = backStackEntry.savedStateHandle.get<String>(ID)
        val userPwd = backStackEntry.savedStateHandle.get<String>(PASSWORD)
        val userNickname = backStackEntry.savedStateHandle.get<String>(NICKNAME)
        LoginPage(
            onClickLogin = { id, password ->
                if (userId != id) Toast.makeText(context, "아이디가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                else if (userPwd != password) Toast.makeText(context, "비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
                else navController.navigate(route = Screen.Home.route + "/${userId}/${userPwd}/${userNickname}") {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                    }
            },
            onClickSignUpPage = {
                navController.navigate(route = Screen.SignUp.route)
            }
        )
    }
}

private fun NavGraphBuilder.addSignUpPage(navController: NavController) {
    composable(route = Screen.SignUp.route) {
        val signUpStateFlow = MutableStateFlow<SignUpState>(SignUpState.Loading)
        val signUpState by signUpStateFlow.collectAsState()

        SignUpPage(
            onStateChange = {
                signUpStateFlow.value = it
            })

        when (signUpState) {
            SignUpState.Loading -> Unit

            SignUpState.ErrorId -> {
                Toast.makeText(LocalContext.current, "아이디가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }

            SignUpState.ErrorPassword -> {
                Toast.makeText(LocalContext.current, "비밀번호가 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }

            SignUpState.ErrorNickname -> {
                Toast.makeText(LocalContext.current, "닉네임이 잘못되었습니다.", Toast.LENGTH_SHORT).show()
            }

            is SignUpState.Success -> {
                navController.previousBackStackEntry?.savedStateHandle?.apply {
                    val data = signUpState as SignUpState.Success
                    set(ID, data.id)
                    set(PASSWORD, data.password)
                    set(NICKNAME, data.nickname)
                }
                navController.popBackStack()
            }
        }
    }
}

private fun NavGraphBuilder.addHomePage(navController: NavController) {
    composable(
        route = Screen.Home.route + "/{arg1}/{arg2}/{arg3}",
        arguments = listOf(
            navArgument("arg1") { type = NavType.StringType },
            navArgument("arg2") { type = NavType.StringType },
            navArgument("arg3") { type = NavType.StringType }
        )
    ) { entry ->
        val id = entry.arguments?.getString("arg1")
        val password = entry.arguments?.getString("arg2")
        val nickname = entry.arguments?.getString("arg3")
        HomePage(
            id = id ?: "",
            password = password ?: "",
            nickname = nickname ?: "",
            modifier = Modifier.padding(16.dp).fillMaxSize()
        )
    }
}

