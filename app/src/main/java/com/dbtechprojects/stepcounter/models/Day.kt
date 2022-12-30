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

fun Day.getDayText(): String {
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return LocalDate.parse(this.date, format).dayOfWeek.name.lowercase(Locale.ROOT)
        .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
}

fun Day.getMockData():List<Day>{
    val format = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    return listOf(
        this,
        this.copy(id = this.id?.plus(1),
            date = LocalDate.parse(this.date, format).minusDays(1).format(format), steps = 3000),
        this.copy(id = this.id?.plus(2),
            date = LocalDate.parse(this.date, format).minusDays(2).format(format), steps = 4000),
        this.copy(id = this.id?.plus(3),
            date = LocalDate.parse(this.date, format).minusDays(3).format(format), steps = 3000),
        this.copy(id = this.id?.plus(4),
            date = LocalDate.parse(this.date, format).minusDays(4).format(format), steps = 5000),
        this.copy(id = this.id?.plus(5),
            date = LocalDate.parse(this.date, format).minusDays(5).format(format), steps = 7500),
        this.copy(id = this.id?.plus(6),
            date = LocalDate.parse(this.date, format).minusDays(6).format(format), steps = 7000),
    )
}

fun List<Day>.getBarData(): List<BarChartData.Bar>{
    val sortedList = this.toMutableList()
        sortedList.sortBy{it.date}
    val list = mutableListOf<BarChartData.Bar>()

    sortedList.forEach { activity ->
        val bar = BarChartData.Bar(activity.steps.toFloat(), color = lightGreen, activity.getDayText().first().toString())
        list.add(bar)
    }

    return list
}
