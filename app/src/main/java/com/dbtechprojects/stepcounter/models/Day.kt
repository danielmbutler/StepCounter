package com.dbtechprojects.stepcounter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dbtechprojects.stepcounter.Constants
import com.dbtechprojects.stepcounter.ui.theme.lightGreen
import me.bytebeats.views.charts.bar.BarChartData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "Days", indices = [Index(value = ["date"], unique = true)])
data class Day(
    @PrimaryKey()    val id: Int? = null,
    @ColumnInfo(name = "date")  val date: String = Constants.getCurrentDate(),
    @ColumnInfo(name = "steps") val steps: Int = 0
)

fun Day.getDayOfWeekText(): String {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this.date, format).dayOfWeek.name.lowercase(Locale.ROOT)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}


// if full week avaiable return full week else return fake data
fun List<Day>.getChartData():List<Day>{
    if (this.size == 7){
        return this
    }

    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    val newActivityList = mutableListOf<Day>()
    newActivityList.addAll(this)

    val remainingDaysInWeek = 7 - this.size
    val last = this.last()

    for (i in 1..remainingDaysInWeek){
        newActivityList.add( last.copy(id = last.id?.plus(i),
            date = LocalDate.parse(last.date, format).minusDays(i.toLong()).format(format), steps = 0))
    }
    return newActivityList
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

fun Day.isToday(): Boolean {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this.date, format).isEqual(LocalDate.now())
}

fun Day.getDayString(): String {
    return if (this.isToday()){
        "Today"
    } else {
        "on this day"
    }
}
