package org.choleemduo.doitnow.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
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
import org.choleemduo.doitnow.viewmodel.HomeViewModel

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    isLoggedIn: Boolean,
    onLogStatus: () -> Unit,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = if (isLoggedIn) Screen.Manage.route else Screen.Login.route
    ) {
        composable(route = Screen.Login.route) {
            LoginScreen(onClicked = {
                onLogStatus()
                navController.navigate(Screen.Manage.route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        inclusive = true
                    }
                }
            })
        }
        composable(route = Screen.Manage.route) {
            val viewModel: HomeViewModel = viewModel()
            ManageScreen(viewModel)
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
            SettingScreen()
        }
    }
}