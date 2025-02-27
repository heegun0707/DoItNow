package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.choleemduo.doitnow.ui.theme.DoItNowTheme

@Composable
fun DefaultInputText(
    titleString: String = "",
    hintString: String = "",
    text: String = "",
    onTextChange: (String) -> Unit,
    modifier: Modifier
) {
    var isFocused by remember { mutableStateOf(false) }
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier
    ) {
        Text(
            text = titleString,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(1.dp)
        )
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = TextStyle(fontSize = 12.sp, color = colorScheme.onSecondary),
            cursorBrush = SolidColor(colorScheme.onSecondary),
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
                .onFocusChanged { focusState -> isFocused = focusState.isFocused },
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (text.isEmpty()) {
                        Text(
                            text = hintString,
                            style = TextStyle(fontSize = 12.sp, color = colorScheme.tertiary)
                        )
                    }
                    innerTextField()
                }
            }
        )
        HorizontalDivider(
            color = colorScheme.run { if (isFocused) primary else onSecondary },
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Composable
fun ErrorInputText(
    hintString: String = "",
    text: String = "",
    errorText: String = "",
    onTextChange: (String) -> Unit,
    modifier: Modifier,
    error: Boolean = false
) {
    var isFocused by remember { mutableStateOf(false) }
    val colorScheme = MaterialTheme.colorScheme

    Column(
        modifier = modifier
    )
    {
        Box(
            modifier = Modifier
                .height(43.dp)
                .fillMaxWidth()
        ) {
            BasicTextField(
                value = text,
                onValueChange = onTextChange,
                textStyle = TextStyle(fontSize = 16.sp, color = colorScheme.onSecondary),
                cursorBrush = SolidColor(colorScheme.onSecondary),
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = 16.dp)
                    .onFocusChanged { focusState -> isFocused = focusState.isFocused },
                decorationBox = { innerTextField ->
                    if (text.isEmpty()) {
                        Text(
                            text = hintString,
                            style = TextStyle(fontSize = 16.sp, color = colorScheme.tertiary)
                        )
                    }
                    innerTextField()
                }
            )
        }
        HorizontalDivider(
            color = colorScheme.run { if (isFocused) primary else onSecondary },
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.Start)
        )
        if (error && text.isEmpty()) {
            Text(
                text = errorText,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(start = 14.dp, top = 8.dp),
                fontSize = 10.sp,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

@Composable
fun ButtonInputText(
    titleString: String = "",
    hintString: String = "",
    text: String = "",
    modifier: Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val colorScheme = MaterialTheme.colorScheme
    Box(
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
            text = titleString,
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(1.dp)
        )
        Text(
            text = text.ifEmpty { hintString },
            fontSize = 12.sp,
            color = colorScheme.run { if (text.isEmpty()) tertiary else onSecondary },
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, bottom = 16.dp)
        )
        HorizontalDivider(
            color = colorScheme.onSecondary,
            thickness = 1.dp,
            modifier = Modifier.align(Alignment.BottomStart)
        )
    }
}

@Composable
fun BaseInputText(
    titleString: String = "",
    hintString: String = "",
    text: String = "",
    textSize: TextUnit = 12.sp,
    errorText: String? = null,
    onTextChange: (String) -> Unit,
    modifier: Modifier,
    isError: Boolean = false,
    isEnabled: Boolean = true,
    maxLines: Int = 1
) {
    val colorScheme = MaterialTheme.colorScheme

    Column(modifier = modifier) {
        if (titleString.isNotEmpty()) {
            Text(
                text = titleString,
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
            textStyle = TextStyle(fontSize = textSize),
            enabled = isEnabled,
            placeholder = {
                Text(
                    text = hintString,
                    style = TextStyle(fontSize = textSize, color = colorScheme.tertiary)
                )
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
            BaseInputText(
                titleString = "타이틀",
                hintString = "타이틀을 입력해주세요.",
                text = text,
                textSize = 16.sp,
                modifier = Modifier
                    .width(width = 363.dp)
                    .wrapContentHeight()
                    .offset(y = 100.dp)
                    .align(Alignment.TopCenter),
                onTextChange = { text = it },
                errorText = "에러 메세지 입니다",
                isEnabled = enabled,
                isError = error,
                maxLines = 3
            )
        }
    }
}
