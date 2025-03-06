package org.choleemduo.doitnow

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.choleemduo.doitnow.ui.navigation.Screen
import org.choleemduo.doitnow.ui.screens.AddScreen
import org.choleemduo.doitnow.ui.screens.AlarmScreen
import org.choleemduo.doitnow.ui.screens.CollectionScreen
import org.choleemduo.doitnow.ui.screens.LoginScreen
import org.choleemduo.doitnow.ui.screens.ManageScreen
import org.choleemduo.doitnow.ui.screens.SettingScreen
import org.choleemduo.doitnow.ui.theme.DoItNowTheme
import org.choleemduo.doitnow.viewmodel.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val homeViewModel: HomeViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener { splashScreen ->
                    splashScreen.remove()
                }
        } else {
            // TODO 버전 12이하 스플래시 화면 동작 정의
        }

        setContent {
            DoItNowTheme {
                val navController = rememberNavController()
                MainApp(navController)
            }
        }
    }

    @Composable
    fun MainApp(
        navController: NavHostController
    ) {
        var isLoggedIn by remember { mutableStateOf(false) }
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (isLoggedIn) {
                    MainBottomNavigation(navController)
                }
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .background(Color.Unspecified)
            ) {
                MainNavHost(
                    navController = navController,
                    isLoggedIn = isLoggedIn
                ) {
                    isLoggedIn = true
                }
            }
        }
    }

    @Composable
    private fun MainBottomNavigation(navController: NavHostController) {
        val items: List<Screen> = listOf(
            Screen.Manage,
            Screen.Collection,
            Screen.Add,
            Screen.Alarm,
            Screen.Setting,
        )
        // TODO 해당 코드 없어도 innerPadding으로 하단 시스템바와 겹치지 않도록 동작 해야함
//        val insets = WindowInsets.systemBars.asPaddingValues()
        BottomNavigation(
            backgroundColor = Color.White,
            elevation = 10.dp,
//            modifier = Modifier.padding(insets)
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { screen ->
                NavigationBarItem(
                    label = {
                        Text(text = stringResource(screen.resourceId))
                    },
                    selected = currentRoute == screen.route,

                    colors = NavigationBarItemDefaults.colors(
                        selectedIconColor = Color.White,
                        selectedTextColor = Color.Black,
                        unselectedIconColor = Color.Black,
                        unselectedTextColor = Color.Black,
                        indicatorColor = Color(0xFF59BF97)
                    ),
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(screen.iconId),
                            contentDescription = screen.route,
                            modifier = Modifier.size(24.dp)
                        )
                    },
                )
            }
        }

    }

    @Composable
    private fun MainNavHost(
        navController: NavHostController,
        isLoggedIn: Boolean,
        onLogStatus: () -> Unit,
    ) {
        NavHost(
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
                ManageScreen(homeViewModel)
            }
            composable(route = Screen.Collection.route) {
                CollectionScreen(this@MainActivity)
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

    @Preview(showBackground = false)
    @Composable
    fun GreetingPreview() {
        DoItNowTheme {
        }
    }
}