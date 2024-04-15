package com.koreatech.kwanhee_jo_compose_study.composable

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.koreatech.kwanhee_jo_compose_study.Constants
import com.koreatech.kwanhee_jo_compose_study.data.LoginData
import com.koreatech.kwanhee_jo_compose_study.ui.Screen
import com.koreatech.kwanhee_jo_compose_study.utils.clearStackNavigate
import com.koreatech.kwanhee_jo_compose_study.utils.showToast
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginPage
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginSideEffect
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginViewModel
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignupPage
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignupSideEffect
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignupViewModel
import org.orbitmvi.orbit.compose.collectSideEffect

fun NavGraphBuilder.accountComposable(navController: NavHostController) {
    composable(
        route = Screen.Login.route,
        arguments = Screen.Login.navArguments
    ) {
        val focusManager = LocalFocusManager.current
        val context = LocalContext.current
        val loginViewModel: LoginViewModel = hiltViewModel()
        // val loginState by loginViewModel.collectAsState()

        loginViewModel.collectSideEffect {
            when (it) {
                is LoginSideEffect.MoveToHomePage -> {
                    navController.clearStackNavigate(Screen.BottomNavItem.Home.route)
                    val loginData = LoginData(it.id, it.password, it.nickname)
                    navController.currentBackStackEntry?.savedStateHandle?.set("loginData", loginData)
                }

                is LoginSideEffect.MoveToSignUpPage -> navController.navigate(route = Screen.Signup.route)
                is LoginSideEffect.ShowToast -> showToast(context, it.message)
            }
        }

        LoginPage(
            focusManager = focusManager,
            onClickLoginButton = { id, password ->
                it.arguments?.apply {
                    val validId = getString(Constants.ID) ?: ""
                    val validPassword = getString(Constants.PASSWORD) ?: ""
                    val validNickname = getString(Constants.NICKNAME) ?: ""
                    loginViewModel.login(id, validId, password, validPassword, validNickname)
                }
            },
            onClickSignupButton = {
                loginViewModel.signUp()
            }
        )
    }
    composable(
        route = Screen.Signup.route,
        arguments = Screen.Signup.navArguments
    ) {
        val context = LocalContext.current
        val focusManager = LocalFocusManager.current
        val signupViewModel: SignupViewModel = viewModel()

        signupViewModel.collectSideEffect {
            when (it) {
                is SignupSideEffect.ShowToast -> showToast(context, it.message)
                is SignupSideEffect.MoveToLoginPage -> navController.clearStackNavigate(
                    Screen.Signup.createRouteToLogin(it.id, it.password, it.nickname)
                )
            }
        }

        SignupPage(
            focusManager = focusManager,
            onClickSignupButton = { id, password, nickname ->
                signupViewModel.signup(id, password, nickname)
            }
        )
    }
}
