package org.choleemduo.doitnow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.choleemduo.doitnow.R
import org.choleemduo.doitnow.ui.components.LoginButton
import org.choleemduo.doitnow.viewmodel.AuthenticationViewModel

@Composable
fun SettingScreen(
    viewModel: AuthenticationViewModel
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Setting")
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
}

@Preview
@Composable
fun SettingScreenPreview() {
//    SettingScreen()
}