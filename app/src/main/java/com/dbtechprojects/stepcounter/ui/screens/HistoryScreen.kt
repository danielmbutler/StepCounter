package com.dbtechprojects.stepcounter.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbtechprojects.stepcounter.R
import com.dbtechprojects.stepcounter.getBarData
import com.dbtechprojects.stepcounter.ui.theme.Purple700
import com.dbtechprojects.stepcounter.ui.theme.lightGreen
import com.dbtechprojects.stepcountershared.models.*
import me.bytebeats.views.charts.bar.BarChart
import me.bytebeats.views.charts.bar.BarChartData
import me.bytebeats.views.charts.bar.render.label.SimpleLabelDrawer
import me.bytebeats.views.charts.bar.render.yaxis.SimpleYAxisDrawer
import me.bytebeats.views.charts.simpleChartAnimation

@Composable
fun HistoryScreen(days: List<Day>){
    Scaffold( content = { padding ->
        Column(
            Modifier
                .fillMaxWidth()
                .padding(PaddingValues(12.dp)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = stringResource(R.string.walk_history_title),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            BarChart(
                barChartData = BarChartData(days.getChartData().getBarData()),
                animation = simpleChartAnimation(),
                modifier = Modifier.fillMaxWidth().fillMaxHeight(0.45f),
                labelDrawer = SimpleLabelDrawer(labelTextColor = Color.White, drawLocation = SimpleLabelDrawer.DrawLocation.XAxis),
                yAxisDrawer = SimpleYAxisDrawer(labelTextColor = Color.White, labelValueFormatter = {value: Float -> value.toInt().toString()}, drawLabelEvery = 5)

            )
            Spacer(modifier = Modifier.size(36.dp))
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colors.primary),
            ) {

                items(days) { day ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(lightGreen)) {
                        Text(text = " ${day.getDayOfWeekText()}", fontWeight = FontWeight.Bold)
                        Text(text = " You completed ${day.steps} steps ${day.getDayString()} !", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.size(6.dp))
                    }
                    Spacer(Modifier.height(12.dp).background(Purple700).fillMaxWidth())
                }
            }
        }
    })
}