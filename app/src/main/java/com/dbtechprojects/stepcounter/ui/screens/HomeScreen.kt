package com.dbtechprojects.stepcounter.ui.screens

import android.hardware.SensorEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.dbtechprojects.stepcounter.Constants
import com.dbtechprojects.stepcounter.ui.theme.Purple700

@Composable
fun HomeScreen(
    count: MutableState<Int?>,
    runImgContentDescription: String,
    navController: NavHostController,
    showPermDeniedScreen: MutableState<Boolean>
) {
    if (showPermDeniedScreen.value) {
        PermissionDeniedScreen()
        return
    }
    Scaffold(floatingActionButton = { HistoryFab(navController) }, content = { padding ->
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(com.dbtechprojects.stepcounter.R.string.home_screen_title),
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            Modifier
                .fillMaxSize()
                .padding(padding),
            Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                imageVector = ImageVector.vectorResource(id = com.dbtechprojects.stepcounter.R.drawable.run_img),
                contentDescription = runImgContentDescription,
                Modifier.size(120.dp),
            )
            StepText(count)
        }
    })
}

@Composable
fun StepText(steps: MutableState<Int?>) {
    if (steps.value != null) {
        Text(
            text = "You have done ${steps.value} steps today!",
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
    } else {
        CircularProgressIndicator()
    }
}

@Composable
fun HistoryFab(navController: NavHostController) {
    return FloatingActionButton(
        onClick = { navController.navigate(Constants.HISTORY_SCREEN) },
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.size(52.dp)
    ) {
        Icon(
            ImageVector.vectorResource(id = com.dbtechprojects.stepcounter.R.drawable.calender),
            contentDescription = stringResource(com.dbtechprojects.stepcounter.R.string.walk_history),
            tint = Purple700,
            modifier = Modifier.size(32.dp)
        )

    }
}

fun SensorEvent.getValue(): Int {
    return (this.values?.get(0) ?: 0).toInt()
}