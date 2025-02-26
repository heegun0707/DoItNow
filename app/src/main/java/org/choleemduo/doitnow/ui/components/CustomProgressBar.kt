package org.choleemduo.doitnow.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.random.Random

@Composable
fun BaseProgressBar(
    progressValue: Float = 0.0f,
    leadingText: String? = null,
    trailingText: String? = null
) {
    val colorScheme = MaterialTheme.colorScheme
    Box(
        modifier = Modifier
            .size(300.dp, 40.dp)
            .drawBehind {
                containerProgress(size, colorScheme.outline)
                contentProgress(size, colorScheme.primary, progressValue)
            },
        contentAlignment = Alignment.CenterStart
    ) {
        leadingText?.let {
            Text(
                text = leadingText,
                modifier = Modifier
                    .padding(start = 16.dp),
                fontSize = 12.sp
            )
        }
        trailingText?.let {
            Text(
                text = trailingText,
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .padding(end = 16.dp),
                fontSize = 12.sp
            )
        }
    }
}

fun DrawScope.containerProgress(
    componentSize: Size,
    stokeColor: Color
) {
    val radius = 24.dp.toPx()
    drawRoundRect(
        color = Color.White,
        cornerRadius = CornerRadius(radius, radius),
        size = componentSize,
        style = Fill
    )
    drawRoundRect(
        color = stokeColor,
        cornerRadius = CornerRadius(radius, radius),
        size = componentSize,
        style = Stroke(
            width = 1.dp.toPx()
        )
    )
}

fun DrawScope.contentProgress(
    componentSize: Size,
    color: Color,
    progress: Float
) {
    val radius = 24.dp.toPx()
    drawRoundRect(
        color = color,
        cornerRadius = CornerRadius(radius, radius),
        size = componentSize.copy(width = progress * componentSize.width),
        style = Fill
    )
}

@Preview(showBackground = true)
@Composable
fun ProgressBarPreviews() {
    var random by remember { mutableFloatStateOf(0f) }
    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val progress = (random * 10).toInt()
        BaseProgressBar(
            progressValue = random,
            leadingText = "오늘 할 일을 ${progress * 100}% 완료했습니다.",
            trailingText = "$progress/10"
        )

        Spacer(modifier = Modifier.padding(16.dp))

        PrimaryButton(text = "Random") {
            random = Random.nextInt(1,10).toFloat() / 10.0f
        }
    }
}