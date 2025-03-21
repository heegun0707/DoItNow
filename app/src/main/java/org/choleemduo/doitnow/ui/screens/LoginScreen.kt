package org.choleemduo.doitnow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import org.choleemduo.doitnow.R
import org.choleemduo.doitnow.ui.components.LoginButton
import org.choleemduo.doitnow.ui.theme.DoItNowTheme
import org.choleemduo.doitnow.viewmodel.AuthenticationViewModel

@Composable
fun LoginScreen(
    viewModel: AuthenticationViewModel = hiltViewModel(),
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        Icon(
            imageVector = Icons.Default.Star,
            contentDescription = "app icon",
            modifier = Modifier.size(80.dp)
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            KakaoButton(viewModel)
            GoogleButton(viewModel)
            LogoutButton(viewModel)
        }
    }
}

@Composable
fun KakaoButton(
    viewModel: AuthenticationViewModel,
) {
    LoginButton(
        text = LocalContext.current.getString(R.string.kakao_login),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFFFEE500),
            contentColor = Color(0XDD000000)
        ),
        icon = {
            Icon(
                painter = painterResource(R.drawable.icon_kakao),
                contentDescription = "kakao button",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
    ) {
        viewModel.loginWithKakao()
    }
}

@Composable
fun GoogleButton(
    viewModel: AuthenticationViewModel,
) {
    LoginButton(
        text = LocalContext.current.getString(R.string.google_login),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFFF2F2F2),
            contentColor = Color(0XDD000000)
        ),
        icon = {
            Icon(
                painter = painterResource(R.drawable.icon_google),
                contentDescription = "google button",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
        }
    ) {
//        viewModel.loginWithGoogle()
    }
}

@Composable
fun LogoutButton(
    viewModel: AuthenticationViewModel
) {
    LoginButton(
        text = LocalContext.current.getString(R.string.logout),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFF92DCE4),
            contentColor = Color.Black
        ),
    ) {
        viewModel.logout()
    }
}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DoItNowTheme {
        LoginScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    Column {
//        KakaoButton({})
//        GoogleButton({})
//        LogoutButton()
    }
}