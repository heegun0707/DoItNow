package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun BaseTab(
    options: List<String>,
    selectedOption: String,
    modifier: Modifier,
    onOptionSelected: (String) -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Row(modifier = Modifier.padding(16.dp)) {
        options.forEach { option ->
            OutlinedButton(
                onClick = { onOptionSelected(option) },
                border = BorderStroke(1.dp, if (selectedOption == option) colorScheme.primary else colorScheme.outline),
                shape = RoundedCornerShape(6.dp),
                modifier = modifier,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = if (selectedOption == option) colorScheme.primary else Color.Transparent,
                    contentColor = if (selectedOption == option) colorScheme.onSecondary else colorScheme.surface
                )
            ) {
                Text(
                    text = option,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}


@Preview
@Composable
fun TabPreviews() {
    val options = listOf("매일", "매주", "매월", "매년")
    var selectedOption by remember { mutableStateOf(options[0]) }
    BaseTab(
        options = options,
        selectedOption = selectedOption,
        modifier = Modifier
            .size(width = 80.dp, height = 48.dp)
            .padding(horizontal = 4.dp),
        onOptionSelected = { selectedOption = it })
}