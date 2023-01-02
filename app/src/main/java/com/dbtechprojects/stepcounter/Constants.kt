package com.dbtechprojects.stepcounter

import com.dbtechprojects.stepcounter.ui.theme.lightGreen
import com.dbtechprojects.stepcountershared.models.Day
import com.dbtechprojects.stepcountershared.models.getDayOfWeekText
import me.bytebeats.views.charts.bar.BarChartData


object Constants {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val HISTORY_SCREEN = "HISTORY_SCREEN"
}

fun List<Day>.getBarData(): List<BarChartData.Bar>{
    val sortedList = this.toMutableList()
    sortedList.sortBy{it.date}
    val list = mutableListOf<BarChartData.Bar>()

    sortedList.forEach { activity ->
        val bar = BarChartData.Bar(activity.steps.toFloat(), color = lightGreen, activity.getDayOfWeekText().first().toString())
        list.add(bar)
    }

    return list
}
