package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DefaultButtons(
    text: String,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onButtonClicked: () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val colors = ButtonDefaults.buttonColors(
        containerColor = when {
            !enabled -> Color(0x4D59BF87)
            isPressed -> Color(0xFF59BF97)
            else -> Color.White
        },
        contentColor = when {
            !enabled -> Color(0x8059BF97)
            isPressed -> Color.White
            else -> Color(0xFF59BF87)
        }
    )

    Button(
        onClick = onButtonClicked,
        modifier = Modifier.size(width = 235.dp, height = 48.dp),
        enabled = enabled,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(width = 1.dp, color = Color(0xFF59BF87)),
        colors = colors,
        interactionSource = interactionSource
    ) {
        Text(text)
    }
}

@Preview
@Composable
fun ButtonPreviews() {
    DefaultButtons("Label", onButtonClicked = {})
}