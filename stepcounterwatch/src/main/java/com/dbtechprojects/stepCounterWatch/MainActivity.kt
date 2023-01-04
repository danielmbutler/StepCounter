package com.dbtechprojects.stepCounterWatch


import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.*
import com.dbtechprojects.stepCounterWatch.service.StepCounterService
import com.dbtechprojects.stepCounterWatch.theme.StepCounterTheme
import com.dbtechprojects.stepcountershared.models.Day
import com.dbtechprojects.stepcountershared.models.MainViewModel
import com.dbtechprojects.stepcountershared.models.getDayOfWeekText
import com.dbtechprojects.stepcountershared.models.isServiceRunning


class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private var count = mutableStateOf<Int?>(null)
    private var activityLast7Days = mutableStateOf( listOf<Day>())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(StepCounterApp.getDao())
        startStepCounterService()
        observeViewModel()
            setContent {
                StepCounterTheme() {
                    HomeScreen(count, activityLast7Days)
                }
            }

    }

    private fun observeViewModel(){
        viewModel.count.observe(this){ count ->
            this.count.value = count
        }
        viewModel.activityInLastWeek.observe(this){ value ->
            Log.d("value", "$value")
            this.activityLast7Days.value = value
        }
    }

    private fun startStepCounterService(){
        if (!isServiceRunning(StepCounterService::class.qualifiedName, StepCounterApp.getApplicationContext())) {
            startForegroundService(Intent(this, StepCounterService::class.java))
        }
    }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startStepCounterService()
            }
        }


    override fun onResume() {
        super.onResume()
        requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
    }

}


@Composable
fun HomeScreen(count: MutableState<Int?>, activityLast7Days: MutableState<List<Day>>) {
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
               textModifier =  Modifier.align(Alignment.CenterHorizontally),
           count
           )
           StepsList(activityLast7Days)

        }


    }
}

@Composable
fun StepsList(activityLast7Days: MutableState<List<Day>>) {
    val listState = rememberScalingLazyListState()
    ScalingLazyColumn(
        modifier = Modifier.fillMaxSize(),
        autoCentering = AutoCenteringParams(itemIndex = 0),
        state = listState
    ){
        items(activityLast7Days.value){ day ->
            Chip(
                colors = ChipDefaults.chipColors(
                    contentColor = Color.White,
                    backgroundColor = Color.DarkGray
                ),
                onClick = { /* ... */ },
                label = {
                    Text(
                        text = "${day.getDayOfWeekText()}: ${day.steps} Steps",
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
fun StepDisplay(imageModifier: Modifier, textModifier: Modifier, count: MutableState<Int?>) {
    Image(
        imageVector = ImageVector.vectorResource(id = com.dbtechprojects.stepcountershared.R.drawable.run_img),
        contentDescription = stringResource(R.string.app_icon),
        modifier = imageModifier,
    )
    Text(
        textAlign = TextAlign.Center,
        modifier = textModifier,
        color = MaterialTheme.colors.primary,
        text = "${count.value ?: 0} Steps"
    )
}