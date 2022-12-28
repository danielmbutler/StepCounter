package com.dbtechprojects.stepcounter

import java.time.LocalDate
import java.time.format.DateTimeFormatter

object Constants {
    const val HOME_SCREEN = "HOME_SCREEN"
    const val HISTORY_SCREEN = "HISTORY_SCREEN"

    fun getCurrentDate(): String {
       return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
    }
}