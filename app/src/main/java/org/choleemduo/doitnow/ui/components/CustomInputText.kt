package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.choleemduo.doitnow.ui.theme.DoItNowTheme

@Composable
fun ButtonInputText(
    titleText: String = "",
    hintText: String = "",
    text: String = "",
    modifier: Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    val typography = MaterialTheme.typography
    Column (
        modifier = modifier
            .clickable(
                onClick = { onClick() },
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                enabled = enabled
            )
            .padding(0.dp)
    ) {
        Text(
            text = titleText,
            style = typography.titleMedium,
            modifier = Modifier
                .align(Alignment.Start)
                .padding(1.dp)
        )
        Text(
            text = text.ifEmpty { hintText },
            style = typography.bodyMedium,
            color = colorScheme.run { if (text.isEmpty()) tertiary else onSecondary },
            modifier = Modifier
                .align(Alignment.Start)
                .padding(start = 16.dp, top = 20.dp, bottom = 16.dp)
        )
        HorizontalDivider(
            color = colorScheme.onSecondary,
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.Start)
        )
    }
}

@Composable
fun BaseInputText(
    titleText: String? = null,
    hintText: String? = null,
    text: String,
    textStyle: TextStyle = MaterialTheme.typography.bodySmall,
    errorText: String? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    maxLines: Int = 1
) {
    val colorScheme = MaterialTheme.colorScheme
    Column(modifier = modifier) {
        titleText?.let {
            Text(
                text = titleText,
                modifier = Modifier
                    .padding(top = 1.dp, start = 1.dp),
                color = if (isEnabled) colorScheme.onSecondary else colorScheme.tertiary
            )
        }
        TextField(
            value = text,
            onValueChange = onTextChange,
            modifier = Modifier
                .fillMaxWidth(),
            textStyle = textStyle,
            enabled = isEnabled,
            placeholder = {
                hintText?.let{
                    Text(
                        text = hintText,
                        style = textStyle,
                        color = colorScheme.tertiary
                    )
                }
            },
            isError = isError,
            minLines = 1,
            maxLines = maxLines,
            singleLine = maxLines == 1,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = colorScheme.primary,
                focusedContainerColor = Color.Transparent,
                unfocusedIndicatorColor = colorScheme.onSecondary,
                unfocusedContainerColor = Color.Transparent,
                errorTextColor = colorScheme.error,
                errorSupportingTextColor = colorScheme.error,
                errorIndicatorColor = colorScheme.error,
                errorContainerColor = Color.Transparent,
                disabledTextColor = colorScheme.tertiary,
                disabledContainerColor = Color.Transparent,
                disabledIndicatorColor = colorScheme.tertiary
            )
        )
        errorText?.let {
            if (isError) {
                Text(
                    text = it,
                    modifier = Modifier.padding(top = 8.dp, start = 10.dp),
                    fontSize = 10.sp,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }

    }


}

@Preview
@Composable
fun InputTextPreviews() {
    DoItNowTheme {
        var text by rememberSaveable { mutableStateOf("") }
        var error by rememberSaveable { mutableStateOf(false) }
        var enabled by rememberSaveable { mutableStateOf(true) }
        Box(
            Modifier
                .size(width = 412.dp, height = 917.dp)
                .background(Color.White)
        ) {
            ButtonInputText(
                titleText = "타이틀",
                hintText = "타이틀을 입력해주세요.",
                text = text,
                modifier = Modifier
                    .width(width = 363.dp)
                    .wrapContentHeight()
                    .offset(y = 100.dp)
                    .align(Alignment.TopCenter),
                onClick = {}
            )
        }
    }
}
