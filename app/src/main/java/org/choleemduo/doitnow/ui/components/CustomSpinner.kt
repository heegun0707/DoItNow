package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.choleemduo.doitnow.R
import org.choleemduo.doitnow.ui.theme.DoItNowTheme

@Composable
fun BaseSpinner(
    text: String,
    textSize: TextUnit,
    centerSpacing: Dp,
    modifier: Modifier,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onButtonClicked: () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val colorScheme = MaterialTheme.colorScheme
    val colors = ButtonDefaults.buttonColors(
        containerColor = when {
            isPressed -> colorScheme.primary
            else -> colorScheme.onPrimary
        },
        contentColor = when {
            isPressed -> colorScheme.onPrimary
            else -> colorScheme.onTertiary
        }
    )
    val buttonBorderColor = colorScheme.run { if (isPressed) outline else Color.Transparent }
    Button(
        onClick = onButtonClicked,
        modifier = modifier,
        enabled = true,
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(width = 1.dp, color = buttonBorderColor),
        colors = colors,
        interactionSource = interactionSource,
        contentPadding = PaddingValues(0.dp)
    ) {
        Row {
            Text(
                text = text,
                fontSize = textSize
            )
            Spacer(modifier = Modifier.width(centerSpacing))
            Image(
                painter = painterResource(if (isPressed) R.drawable.icon_arrow_drop_down_white else R.drawable.icon_arrow_drop_down),
                contentDescription = "dropDown",
                modifier = Modifier.size(24.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}


@Preview
@Composable
fun SpinnerPreviews() {
    var spinnerText by rememberSaveable { mutableStateOf("yyyy년 MM월 dd일 E요일") }
    DoItNowTheme {
        BaseSpinner(
            text = spinnerText,
            textSize = 16.sp,
            centerSpacing = 2.dp,
            modifier = Modifier.size(width = 370.dp, height = 42.dp),
            onButtonClicked = {})
    }
    LaunchedEffect(Unit) {
        spinnerText = "변경되나"
    }
}