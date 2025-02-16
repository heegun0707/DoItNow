package org.choleemduo.doitnow

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.FontScaling
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.AndroidEntryPoint
import org.choleemduo.doitnow.ui.components.DefaultButtons
import org.choleemduo.doitnow.ui.theme.DoItNowTheme
import org.choleemduo.doitnow.viewmodel.HomeViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DoItNowTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) {
                    ShowAdvice()
                }
            }
        }
    }
}

@Composable
private fun ShowAdvice(homeViewModel: HomeViewModel = viewModel()) {
    val adviceState = homeViewModel.advice.collectAsState(initial = "")
    Column (
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = adviceState.value,
            color = Color.Black,
            fontSize = 24.sp
        )

        DefaultButtons("Random") {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DoItNowTheme {
        ShowAdvice()
    }
}