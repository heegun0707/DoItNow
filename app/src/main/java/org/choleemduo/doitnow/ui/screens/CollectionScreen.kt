package org.choleemduo.doitnow.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import org.choleemduo.doitnow.ui.components.InputBoxPreviews
import org.choleemduo.doitnow.ui.components.PrimaryButton

@Composable
fun CollectionScreen(context: Context) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text("Collection")
        InputBoxPreviews()

        Button(onClick = {
            Toast.makeText(context, "안녕하세요", Toast.LENGTH_SHORT).show()
        }) {
            Text("dd")
        }
    }
}

@Preview
@Composable
fun CollectionScreenPreview() {
//    CollectionScreen()
}