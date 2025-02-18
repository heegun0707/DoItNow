package org.choleemduo.doitnow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
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
        val homeViewModel: HomeViewModel = viewModel()
        val items: List<Screen> = listOf(
            Screen.Manage,
            Screen.Collection,
            Screen.Add,
            Screen.Alarm,
            Screen.Setting,
        )
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                BottomNavigation(
                    backgroundColor = Color.White,
                    elevation = 10.dp
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
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Manage.route,
                modifier = Modifier
                    .padding(innerPadding)
            ) {
                composable(route = Screen.Manage.route) {
                    ManageScreen(homeViewModel)
                }
                composable(route = Screen.Collection.route) {
                    CollectionScreen()
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
    }

    @Preview(showBackground = false)
    @Composable
    fun GreetingPreview() {
        DoItNowTheme {
        }
    }
}