package org.choleemduo.doitnow

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import org.choleemduo.data.manager.KakaoAuthManager
import org.choleemduo.doitnow.ui.components.MainBottomNavigation
import org.choleemduo.doitnow.ui.navigation.MainNavHost
import org.choleemduo.doitnow.ui.theme.DoItNowTheme
import org.choleemduo.doitnow.utils.DoItUtils
import org.choleemduo.doitnow.viewmodel.AuthenticationViewModel
import org.choleemduo.domain.manager.AuthState
import org.choleemduo.domain.manager.SocialNetwork
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: AuthenticationViewModel by viewModels()
    @Inject
    lateinit var kakaoAuthManager: KakaoAuthManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setOnExitAnimationListener {
                viewModel.checkLoginRequired()
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
        viewModel: AuthenticationViewModel
    ) {
        when (val authState = viewModel.authState.collectAsStateWithLifecycle().value) {
            is AuthState.Loading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    CircularProgressIndicator()
                }
            }
            else -> {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        if (authState is AuthState.LoginSuccess) {
                            MainBottomNavigation(navController)
                        }
                    }
                ) { innerPadding ->
                    MainNavHost(
                        Modifier.padding(innerPadding),
                        navController,
                        viewModel,
                        authState
                    )
                }
                when (authState) {
                    is AuthState.LoginSuccess -> {
                        DoItUtils.showToast(this, getString(R.string.login_success))
                        viewModel.setSocialNetworkType(authState.getType())
                    }
                    is AuthState.LoginFail -> {
                        DoItUtils.showToast(this, getString(R.string.login_fail))
                    }
                    is AuthState.LogoutSuccess -> {
                        DoItUtils.showToast(this, getString(R.string.logout_success))
                        viewModel.setSocialNetworkType(SocialNetwork.NONE)
                    }
                    is AuthState.LogoutFail -> {
                        DoItUtils.showToast(this, getString(R.string.logout_fail))
                    }
                    else -> {}
                }
            }
        }
    }
}