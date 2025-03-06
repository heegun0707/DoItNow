package org.choleemduo.doitnow.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.choleemduo.doitnow.R
import org.choleemduo.doitnow.ui.theme.DoItNowTheme

@Composable
fun LoginScreen(onClicked: () -> Unit) {
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
            modifier = Modifier.fillMaxWidth().height(128.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            KakaoButton(onClicked)
            GoogleButton()
        }
    }
}

@Composable
fun KakaoButton(
    onClicked: () -> Unit
) {
    Button(
        modifier = Modifier
            .size(width = 370.dp, height = 56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFFFEE500),
            contentColor = Color(0XDD000000)
        ),
        onClick = onClicked,
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_kakao),
                contentDescription = "kakao button",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "카카오로 계속하기",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Composable
fun GoogleButton() {
    Button(
        modifier = Modifier
            .size(width = 370.dp, height = 56.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0XFFF2F2F2),
            contentColor = Color(0XDD000000)
        ),
        onClick = {},
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            Icon(
                painter = painterResource(R.drawable.icon_google),
                contentDescription = "google button",
                modifier = Modifier.size(24.dp),
                tint = Color.Unspecified
            )
            Text(
                text = "구글로 계속하기",
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    DoItNowTheme {
        LoginScreen({})
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonsPreview() {
    Column{
        KakaoButton({})
        GoogleButton()
    }
}