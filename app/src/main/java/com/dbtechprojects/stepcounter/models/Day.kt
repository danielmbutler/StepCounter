package com.dbtechprojects.stepcounter.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.dbtechprojects.stepcounter.Constants
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Entity(tableName = "Days", indices = [Index(value = ["date"], unique = true)])
data class Day(
    @PrimaryKey(autoGenerate = true)    val id: Int? = null,
    @ColumnInfo(name = "date")  val date: String = Constants.getCurrentDate(),
    @ColumnInfo(name = "steps") val steps: Int = 0
)
