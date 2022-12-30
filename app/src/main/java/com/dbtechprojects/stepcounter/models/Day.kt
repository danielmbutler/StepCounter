package com.dbtechprojects.stepcounter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dbtechprojects.stepcounter.Constants
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
