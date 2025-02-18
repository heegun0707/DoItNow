package org.choleemduo.doitnow.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.choleemduo.doitnow.R

@Composable
fun SplashScreen() {
    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(R.mipmap.ic_launcher),
            contentDescription = "splash icon",
            modifier = Modifier.size(80.dp)
        )
    }
}

@Preview
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}