package com.dbtechprojects.stepcountershared.models


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dbtechprojects.stepcountershared.models.Day
import com.dbtechprojects.stepcountershared.models.ActivityDao


class MainViewModel(private val db: ActivityDao): ViewModel()  {

    val count: LiveData<Int?> = db.getCurrentCount()
    val activityInLastWeek: LiveData<List<Day>> = db.getActivityInLastWeek()

}