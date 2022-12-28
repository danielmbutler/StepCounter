package com.dbtechprojects.stepcounter.persistence
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dbtechprojects.stepcounter.Constants
import com.dbtechprojects.stepcounter.models.Day

@Dao
interface ActivityDao {

    @Query("SELECT * FROM Days ORDER BY date DESC")
    fun getDays() : LiveData<List<Day>>

    @Query("SELECT steps FROM Days WHERE date=:date")
    fun getCurrentCount(date: String = Constants.getCurrentDate()): LiveData<Int?>

}