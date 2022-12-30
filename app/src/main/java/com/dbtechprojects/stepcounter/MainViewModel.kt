package com.dbtechprojects.stepcounter


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dbtechprojects.stepcounter.models.Day
import com.dbtechprojects.stepcounter.persistence.ActivityDao


class MainViewModel(private val db: ActivityDao): ViewModel()  {

    val count: LiveData<Int?> = db.getCurrentCount()
    val activityInLastWeek: LiveData<List<Day>> = db.getActivityInLastWeek()

}