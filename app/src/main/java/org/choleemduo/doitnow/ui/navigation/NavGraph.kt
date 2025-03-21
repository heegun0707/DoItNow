package org.choleemduo.doitnow.ui.navigation

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.choleemduo.doitnow.ui.screens.AddScreen
import org.choleemduo.doitnow.ui.screens.AlarmScreen
import org.choleemduo.doitnow.ui.screens.CollectionScreen
import org.choleemduo.doitnow.ui.screens.LoginScreen
import org.choleemduo.doitnow.ui.screens.ManageScreen
import org.choleemduo.doitnow.ui.screens.SettingScreen
import org.choleemduo.doitnow.viewmodel.AuthenticationViewModel
import org.choleemduo.doitnow.viewmodel.HomeViewModel
import org.choleemduo.domain.manager.AuthState

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    viewModel: AuthenticationViewModel,
    authState: AuthState
) {
    val context = LocalContext.current
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (authState is AuthState.LoginSuccess) Screen.Manage.route else Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(viewModel)
            if (authState is AuthState.LoginSuccess) {
                navController.navigate(Screen.Manage.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            }
        }
        composable(route = Screen.Manage.route) {
            val homeViewModel: HomeViewModel = hiltViewModel()
            ManageScreen(homeViewModel)
        }
        composable(route = Screen.Collection.route) {
            CollectionScreen(LocalContext.current)
        }
        composable(route = Screen.Add.route) {
            AddScreen()
        }
        composable(route = Screen.Alarm.route) {
            AlarmScreen()
        }
        composable(route = Screen.Setting.route) {
            SettingScreen(viewModel)
            if (authState == AuthState.LogoutSuccess) {
                Toast.makeText(context, "로그아웃 성공", Toast.LENGTH_SHORT).show()

                navController.navigate(Screen.Login.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            }
        }
    }
}