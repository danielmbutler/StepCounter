package com.dbtechprojects.stepcounter

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dbtechprojects.stepcounter.persistence.ActivityDao


class MainViewModel(db: ActivityDao): ViewModel()  {

    val count: LiveData<Int?> = db.getCurrentCount()

}