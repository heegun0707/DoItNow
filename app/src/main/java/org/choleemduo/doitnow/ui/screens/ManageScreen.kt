package org.choleemduo.doitnow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.choleemduo.doitnow.ui.components.ButtonPreviews
import org.choleemduo.doitnow.ui.components.PrimaryButton
import org.choleemduo.doitnow.viewmodel.HomeViewModel

@Composable
fun ManageScreen(homeViewModel: HomeViewModel) {
    val advice = homeViewModel.advice.collectAsState("").value
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Manage")
        ShowAdvice(advice) {
            homeViewModel.getAdvice()
        }
        ButtonPreviews()
    }
}

@Composable
private fun ShowAdvice(
    advice: String,
    onChanged: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            modifier = Modifier.padding(bottom = 16.dp),
            text = advice,
            color = Color.Black,
            fontSize = 24.sp
        )

        PrimaryButton(
            text = "Random",
            onButtonClicked = onChanged
        )
    }
}
