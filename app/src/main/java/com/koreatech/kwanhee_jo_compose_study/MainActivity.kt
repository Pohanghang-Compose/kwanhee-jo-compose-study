package com.koreatech.kwanhee_jo_compose_study

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.koreatech.kwanhee_jo_compose_study.Constants.ID
import com.koreatech.kwanhee_jo_compose_study.Constants.NICKNAME
import com.koreatech.kwanhee_jo_compose_study.Constants.PASSWORD
import com.koreatech.kwanhee_jo_compose_study.ui.theme.KwanheeTheme
import com.koreatech.kwanhee_jo_compose_study.view.home.HomePage
import com.koreatech.kwanhee_jo_compose_study.view.home.HomeViewModel
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginPage
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginSideEffect
import com.koreatech.kwanhee_jo_compose_study.view.login.LoginViewModel
import com.koreatech.kwanhee_jo_compose_study.view.navi.BottomNavigationBar
import com.koreatech.kwanhee_jo_compose_study.view.navi.serach.SearchPage
import com.koreatech.kwanhee_jo_compose_study.view.navi.setting.SettingPage
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignUpPage
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignUpSideEffect
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignUpState
import com.koreatech.kwanhee_jo_compose_study.view.signup.SignUpViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KwanheeTheme {
                val navController = rememberNavController()
                val context = LocalContext.current
                val currentRoute by navController.currentBackStackEntryAsState()
                val bottomNaviItems = listOf(
                    Screen.BottomNavItem.Home.route,
                    Screen.BottomNavItem.Search.route,
                    Screen.BottomNavItem.Setting.route
                )

                Scaffold(
                    bottomBar = {
                        if (currentRoute?.destination?.route in bottomNaviItems) {
                            BottomNavigationBar(navController = navController)
                        }
                    }
                ) { paddingValues ->
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Login.route,
                        modifier = Modifier.padding(paddingValues)
                    ) {
                        composable(route = Screen.Login.route) { entry ->
                            val loginViewModel: LoginViewModel = viewModel()
                            val loginState by loginViewModel.collectAsState()

                            entry.savedStateHandle.apply {
                                loginViewModel.updateLoginSavedStateHandle(
                                    id = get<String>(ID) ?: "",
                                    password = get<String>(PASSWORD) ?: "",
                                    nickname = get<String>(NICKNAME) ?: ""
                                )
                            }

                            loginViewModel.collectSideEffect {
                                when (it) {
                                    is LoginSideEffect.MoveToHomePage -> {
                                        navController.navigate(route = Screen.BottomNavItem.Home.route) {
                                            popUpTo(navController.graph.id) {
                                                inclusive = true
                                            }
                                        }
                                        navController.currentBackStackEntry?.savedStateHandle?.apply {
                                            set(ID, loginViewModel.id)
                                            set(PASSWORD, loginViewModel.password)
                                            set(NICKNAME, loginViewModel.nickname)
                                        }
                                    }

                                    is LoginSideEffect.MoveToSignUpPage -> {
                                        navController.navigate(route = Screen.SignUp.route)
                                    }
                                }
                            }

                            LoginPage(
                                state = loginState,
                                context = context,
                                onClickLogin = { id, password ->
                                    loginViewModel.login(id, password)
                                },
                                onClickSignUpPage = {
                                    loginViewModel.signUp()
                                }
                            )
                        }
                        composable(route = Screen.SignUp.route) {
                            val signUpViewModel: SignUpViewModel = viewModel()
                            val signUpState by signUpViewModel.collectAsState()

                            signUpViewModel.collectSideEffect {
                                when (it) {
                                    is SignUpSideEffect.BackToLoginPage -> {
                                        navController.previousBackStackEntry?.savedStateHandle?.apply {
                                            set(ID, it.id)
                                            set(PASSWORD, it.password)
                                            set(NICKNAME, it.nickname)
                                        }
                                        navController.popBackStack()
                                    }
                                }
                            }

                            SignUpPage(
                                state = signUpState,
                                context = context,
                                onClickSignUp = { id, password, nickname ->
                                    signUpViewModel.register(id, password, nickname)
                                }
                            )
                        }
                        composable(
                            route = Screen.BottomNavItem.Home.route
                        ) {
                            val homeViewModel: HomeViewModel = composableActivityViewModel()
                            val homeState by homeViewModel.collectAsState()

                            it.savedStateHandle.apply {
                                if (get<String>(ID)?.isNotEmpty() == true) {
                                    homeViewModel.updateHomeData(
                                        id = get<String>(ID) ?: "",
                                        password = get<String>(PASSWORD) ?: "",
                                        nickname = get<String>(NICKNAME) ?: ""
                                    )
                                }
                            }

                            homeViewModel.collectSideEffect {
                                when (it) { }
                            }

                            HomePage(
                                context = context,
                                id = homeViewModel.id,
                                password = homeViewModel.password,
                                nickname = homeViewModel.nickname,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxSize()
                            )
                        }
                        composable(Screen.BottomNavItem.Search.route) {
                            val homeViewModel: HomeViewModel = composableActivityViewModel()
                            SearchPage()
                        }
                        composable(Screen.BottomNavItem.Setting.route) {
                            SettingPage()
                        }
                    }

                }
            }
        }
    }
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