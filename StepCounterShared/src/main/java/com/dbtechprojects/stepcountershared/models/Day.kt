package com.dbtechprojects.stepcountershared.models

import android.hardware.SensorEvent
import android.util.Log
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@Entity(tableName = "Days", indices = [Index(value = ["date"], unique = true)])
data class Day(
    @PrimaryKey()    val id: Int? = null,
    @ColumnInfo(name = "date")  val date: String = getCurrentDate(),
    @ColumnInfo(name = "steps") val steps: Int = 0
)

fun getCurrentDate(): String {
    return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
}

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
