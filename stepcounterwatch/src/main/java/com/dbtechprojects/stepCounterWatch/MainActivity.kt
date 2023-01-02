package com.dbtechprojects.stepCounterWatch


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.dbtechprojects.stepCounterWatch.theme.StepCounterTheme


class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

            setContent {
                StepCounterTheme() {
                    HomeScreen()
                }
            }

    }
}

@Preview
@Composable
fun HomeScreen(){
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .fillMaxSize()
    ) {
        Column( modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center) {
           StepDisplay(
               imageModifier = Modifier
                   .size(64.dp)
                   .align(Alignment.CenterHorizontally),
               textModifier =  Modifier.align(Alignment.CenterHorizontally)
           )
           StepsList()

        }


    }
}

@Composable
fun StepsList(){
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState
    ){
        items(5){
            Chip(
                colors = ChipDefaults.chipColors(
                    contentColor = Color.White,
                    backgroundColor = Color.DarkGray
                ),
                onClick = { /* ... */ },
                label = {
                    Text(
                        text = "Wednesday 4000 Steps",
                        maxLines = 1,
                        fontSize = 12.sp,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            )
        }
    }
}

@Composable
fun StepDisplay(imageModifier: Modifier, textModifier: Modifier) {
    Image(
        imageVector = ImageVector.vectorResource(id = com.dbtechprojects.stepcountershared.R.drawable.run_img),
        contentDescription = stringResource(R.string.app_icon),
        modifier = imageModifier,
    )
    Text(
        textAlign = TextAlign.Center,
        modifier = textModifier,
        color = MaterialTheme.colors.primary,
        text = "30 Steps"
    )
}