package org.choleemduo.doitnow.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with

// val font = Font()

val BaseTextStyle = TextStyle(
    fontStyle = FontStyle.Normal,
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal
)

val CustomTypography = Typography(
    displayLarge = BaseTextStyle.copy(
        fontSize = 24.sp,
        lineHeight = 36.sp
    ),
    titleLarge = BaseTextStyle.copy(
        fontSize = 20.sp
    ),
    titleMedium = BaseTextStyle.copy(
        fontSize = 16.sp
    ),
    bodyLarge = BaseTextStyle.copy(
        fontSize = 14.sp
    ),
    bodyMedium = BaseTextStyle.copy(
        fontSize = 12.sp
    ),
    bodySmall = BaseTextStyle.copy(
        fontSize = 10.sp
    ),
    labelLarge = BaseTextStyle.copy(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    labelMedium = BaseTextStyle.copy(
        fontSize = 12.sp
    )
)