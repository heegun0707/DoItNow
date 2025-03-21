package org.choleemduo.doitnow

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.choleemduo.doitnow.ui.components.MainBottomNavigation
import org.choleemduo.doitnow.ui.navigation.MainNavHost
import org.choleemduo.doitnow.ui.theme.DoItNowTheme
import org.choleemduo.doitnow.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener {
                it.remove()
            }
        } else {
            // TODO 버전 12이하 스플래시 화면 동작 정의
        }

        setContent {
            DoItNowTheme {
                val navController = rememberNavController()
                MainApp(navController, viewModel)
            }
        }
    }

    @Composable
    fun MainApp(
        navController: NavHostController,
        viewModel: MainViewModel
    ) {
        val isLoggedIn = viewModel.isLoggedIn.collectAsStateWithLifecycle(initialValue = false).value
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (isLoggedIn) {
                    MainBottomNavigation(navController)
                }
            }
        ) { innerPadding ->
                MainNavHost(Modifier.padding(innerPadding), navController, isLoggedIn) {
                    viewModel.updateLoginStatus(true)
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