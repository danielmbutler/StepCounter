package com.dbtechprojects.stepcounter.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dbtechprojects.stepcounter.R
import com.dbtechprojects.stepcounter.models.Day
import com.dbtechprojects.stepcounter.models.getDayText
import com.dbtechprojects.stepcounter.ui.theme.lightGreen

@Composable
fun HistoryScreen(days: MutableState<List<Day>>){
    Scaffold( content = { padding ->
        Column(
            Modifier
                .fillMaxSize()
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
            LazyColumn(
                modifier = Modifier.background(MaterialTheme.colors.primary),
            ) {

                items(days.value) { day ->
                    Column(modifier = Modifier
                        .fillMaxSize()
                        .background(lightGreen)) {
                        Text(text = " ${day.getDayText()}", fontWeight = FontWeight.Bold)
                        Text(text = " You completed ${day.steps} steps on this day !", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.size(6.dp))
                    }
                }
            }
        }
    })
}