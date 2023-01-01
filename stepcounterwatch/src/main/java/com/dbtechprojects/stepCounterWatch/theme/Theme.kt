package com.dbtechprojects.stepCounterWatch.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.wear.compose.material.Colors
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ProvideTextStyle
import com.dbtechprojects.stepcounter.ui.theme.Black
import com.dbtechprojects.stepcounter.ui.theme.Teal200
import com.dbtechprojects.stepcounter.ui.theme.white



private val colorPalette = Colors(
    primary = white,
    background = Black,
    secondary = Teal200,

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun StepCounterTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = colorPalette,
        typography = Typography,
        content = {
            ProvideTextStyle(
                value = TextStyle(color = Color.White),
                content = content
            )
        }
    )
}