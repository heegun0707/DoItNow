package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
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
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier.clickable { focusRequester.requestFocus() }
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
                .focusRequester(focusRequester)
                .focusable(interactionSource = interactionSource),
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
    val focusRequester = remember { FocusRequester() }
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused by interactionSource.collectIsFocusedAsState()
    val colorScheme = MaterialTheme.colorScheme

    Box(
        modifier = modifier.clickable { focusRequester.requestFocus() }
    ) {
        Column {
            Box(modifier = Modifier.height(43.dp)){
                BasicTextField(
                    value = text,
                    onValueChange = onTextChange,
                    textStyle = TextStyle(fontSize = 16.sp, color = colorScheme.onSecondary),
                    cursorBrush = SolidColor(colorScheme.onSecondary),
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 16.dp)
                        .focusRequester(focusRequester)
                        .focusable(interactionSource = interactionSource),
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
            .padding(0.dp)
            .clickable(enabled = enabled) { onClick() }
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

@Preview
@Composable
fun InputTextPreviews() {
    DoItNowTheme {
        var text by rememberSaveable { mutableStateOf("") }
        var error by rememberSaveable { mutableStateOf(true) }
        Box(
            Modifier
                .size(width = 412.dp, height = 917.dp)
                .background(Color.White)
        ) {
            ErrorInputText(
                hintString = "일정 타이틀을 작성해주세요.",
                text = text,
                errorText = "에러 메세지 입니다",
                modifier = Modifier
                    .size(width = 363.dp, height = 77.dp)
                    .align(Alignment.Center),
                onTextChange = { text = it },
                error = error
            )
            ButtonInputText(
                titleString = "타이틀",
                hintString = "타이틀을 입력해주세요.",
                text = text,
                modifier = Modifier
                    .size(width = 363.dp, height = 71.dp)
                    .offset(y = 100.dp)
                    .align(Alignment.TopCenter),
                onClick = {}
            )
            DefaultInputText(
                titleString = "타이틀",
                hintString = "타이틀을 입력해주세요.",
                text = text,
                modifier = Modifier
                    .size(width = 363.dp, height = 71.dp)
                    .offset(y = (-100).dp)
                    .align(Alignment.BottomCenter),
                onTextChange = { text = it }
            )
        }
    }
}
