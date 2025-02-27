package org.choleemduo.doitnow.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.choleemduo.doitnow.ui.theme.BlackText
import org.choleemduo.doitnow.ui.theme.CursorColor

@Composable
fun BaseOutlinedTextField(
    text: String = "",
    modifier: Modifier = Modifier,
    onValueChanged: (String) -> Unit,
    isEnabled: Boolean = true,
    isError: Boolean = false,
    placeholder: String = "",
    supportingText: String? = null,
    onTrailingIconClicked: () -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() }
) {
    val focusManager = LocalFocusManager.current
    Column {
        OutlinedTextField(
            value = text,
            onValueChange = onValueChanged,
            modifier = Modifier
                .size(width = 235.dp, height = 56.dp)
                .then(modifier),
            textStyle = MaterialTheme.typography.titleMedium,
            enabled = isEnabled,
            placeholder = {
                Text(
                    text = placeholder,
                    color = BlackText.copy(alpha = 0.5f)
                )
            },
            trailingIcon = {
                AnimatedVisibility(
                    visible = text.isNotEmpty(),
                    enter = fadeIn(),
                    exit = fadeOut()
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "close icon",
                        modifier = Modifier
                            .size(20.dp)
                            .clickable(
                                onClick = { onTrailingIconClicked() },
                                indication = null,
                                interactionSource = interactionSource
                            ),
                        tint = MaterialTheme.colorScheme.error
                    )
                }
            },
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = { focusManager.clearFocus() }
            ),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = MaterialTheme.colorScheme.secondary,
                unfocusedTextColor = MaterialTheme.colorScheme.onSurface,
                focusedContainerColor = MaterialTheme.colorScheme.secondary,
                focusedTextColor = MaterialTheme.colorScheme.onSurface,
                errorContainerColor = MaterialTheme.colorScheme.secondary,
                errorCursorColor = MaterialTheme.colorScheme.error,
                cursorColor = CursorColor

            ),
            shape = RoundedCornerShape(6.dp),
        )
        supportingText?.let {
            if (isError) {
                Text(
                    text = it,
                    modifier = Modifier.padding(top = 4.dp, start = 6.dp),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
        Text(
            text = text
        )
    }
}

@Preview
@Composable
fun InputBoxPreviews() {
    var text by remember { mutableStateOf("") }
    var isError by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .wrapContentHeight()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseOutlinedTextField(
            text = text,
            onValueChanged = {
                text = it
            },
            isError = isError,
            placeholder = "타이틀로 검색하세요.",
            supportingText = "1글자 이상 필수 입력란 입니다.",
            onTrailingIconClicked = {
                text = ""
            }
        )
        PrimaryButton(
            text = if (isError) "에러 해제" else "에러 발생"
        ) {
            isError = isError.not()
        }
    }
}