package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import timber.log.Timber

@Composable
fun BaseRadioButton(
    labelList: List<String>,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClicked: (Pair<Int, String>) -> Unit,
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(labelList[0]) }
    Row(
        modifier = Modifier.wrapContentSize().then(modifier)
    ) {
        labelList.forEachIndexed { index, text ->
            Row(
                modifier = Modifier
                    .wrapContentSize()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            onClicked(index to text)
                        },
                        indication = null,
                        interactionSource = interactionSource,
                        role = Role.RadioButton
                    )
                    .padding(horizontal = 18.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly

            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = null,
                    enabled = isEnabled
                )
                Text(
                    text = text,
                    modifier = Modifier.padding(start = 14.dp),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Preview
@Composable
fun RadioButtonPreviews() {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BaseRadioButton(
            labelList = listOf("시작전", "진행중", "완료")
        ) { (index, label) ->
            Timber.e("index = $index, text = $label")
        }
    }
}