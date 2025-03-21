package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.choleemduo.doitnow.R

@Composable
fun BaseButton(
    text: String,
    modifier: Modifier = Modifier,
    isEnabled: Boolean,
    colors: ButtonColors,
    borderColor: Color? = null,
    interactionSource: MutableInteractionSource,
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        modifier = Modifier
            .size(width = 235.dp, height = 48.dp)
            .then(modifier),
        enabled = isEnabled,
        shape = RoundedCornerShape(6.dp),
        border = borderColor?.let { BorderStroke(width = 1.dp, color = borderColor) },
        colors = colors,
        interactionSource = interactionSource
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium
        )
    }
}

@Composable
fun PrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onButtonClicked: () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val colorScheme = MaterialTheme.colorScheme
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorScheme.run { if (isPressed) primary else onPrimary },
        contentColor = colorScheme.run { if (isPressed) onPrimary else primary },
        disabledContainerColor = colorScheme.onPrimary,
        disabledContentColor = colorScheme.primary.copy(alpha = 0.2f)
    )
    val buttonBorderColor = colorScheme.run { if (enabled) primary else primary.copy(alpha = 0.2f) }

    BaseButton(
        text = text,
        modifier = modifier,
        isEnabled = enabled,
        colors = buttonColors,
        borderColor = buttonBorderColor,
        interactionSource = interactionSource,
        onButtonClicked = onButtonClicked
    )
}

@Composable
fun PositiveButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onButtonClicked: () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val colorScheme = MaterialTheme.colorScheme
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorScheme.run { if (isPressed) onPrimary else primary },
        contentColor = colorScheme.run { if (isPressed) primary else onPrimary },
        disabledContainerColor = colorScheme.primary.copy(alpha = 0.2f),
        disabledContentColor = colorScheme.primary.copy(alpha = 0.2f)
    )
    val buttonBorderColor = if (enabled) colorScheme.primary else null

    BaseButton(
        text = text,
        modifier = modifier,
        isEnabled = enabled,
        colors = buttonColors,
        borderColor = buttonBorderColor,
        interactionSource = interactionSource,
        onButtonClicked = onButtonClicked
    )
}

@Composable
fun NegativeButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onButtonClicked: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorScheme.tertiary,
        contentColor = colorScheme.onTertiary,
        disabledContainerColor = colorScheme.tertiary.copy(alpha = 0.5f),
        disabledContentColor = colorScheme.onTertiary.copy(alpha = 0.5f)
    )

    BaseButton(
        text = text,
        modifier = modifier,
        isEnabled = enabled,
        colors = buttonColors,
        interactionSource = interactionSource,
        onButtonClicked = onButtonClicked
    )
}

@Composable
fun ErrorButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onButtonClicked: () -> Unit
) {
    val isPressed by interactionSource.collectIsPressedAsState()
    val colorScheme = MaterialTheme.colorScheme
    val buttonColors = ButtonDefaults.buttonColors(
        containerColor = colorScheme.run { if (isPressed) onError else error },
        contentColor = colorScheme.run { if (isPressed) error else onError },
        disabledContainerColor = colorScheme.error.copy(alpha = 0.3f),
        disabledContentColor = colorScheme.onError.copy(alpha = 0.3f)
    )
    val buttonBorderColor = if (enabled) colorScheme.error else null

    BaseButton(
        text = text,
        modifier = modifier,
        isEnabled = enabled,
        colors = buttonColors,
        borderColor = buttonBorderColor,
        interactionSource = interactionSource,
        onButtonClicked = onButtonClicked
    )
}

@Composable
fun LoginButton(
    text: String,
    colors: ButtonColors,
    icon: @Composable () -> Unit = {},
    onButtonClicked: () -> Unit
) {
    Button(
        onClick = onButtonClicked,
        modifier = Modifier.padding(horizontal = 20.dp).height(56.dp),
        shape = RoundedCornerShape(12.dp),
        colors = colors
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.CenterStart
        ) {
            icon()
            Text(
                text = text,
                style = MaterialTheme.typography.labelLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview
@Composable
fun ButtonPreviews() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PrimaryButton("Label", onButtonClicked = {})
        PrimaryButton("Label", enabled = false, onButtonClicked = {})
        PositiveButton("Label", onButtonClicked = {})
        PositiveButton("Label", enabled = false, onButtonClicked = {})
        NegativeButton("Label", onButtonClicked = {})
        NegativeButton("Label", enabled = false, onButtonClicked = {})
        ErrorButton("Label", onButtonClicked = {})
        ErrorButton("Label", enabled = false, onButtonClicked = {})
    }
}