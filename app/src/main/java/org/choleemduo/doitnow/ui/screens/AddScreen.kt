package org.choleemduo.doitnow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.choleemduo.doitnow.ui.components.ProgressBarPreviews
import org.choleemduo.doitnow.ui.components.RadioButtonPreviews

@Composable
fun AddScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Add")
        RadioButtonPreviews()
        ProgressBarPreviews()
    }
}

@Preview
@Composable
fun AddScreenPreview() {
    AddScreen()
}