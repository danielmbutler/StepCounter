package com.dbtechprojects.stepcounter.persistence
import androidx.lifecycle.LiveData
import androidx.room.*
import com.dbtechprojects.stepcounter.Constants
import com.dbtechprojects.stepcounter.models.Day

@Dao
interface ActivityDao {

    @Query("SELECT * FROM Days WHERE date > datetime('now', '-7 day')")
    fun getActivityInLastWeek() : LiveData<List<Day>>

    @Query("SELECT steps FROM Days WHERE date=:date")
    fun getCurrentCount(date: String = Constants.getCurrentDate()): LiveData<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(day: Day)

    @Update
    fun updateDay(day: Day)

    @Query("SELECT * FROM Days WHERE date=:date")
    fun getCurrentDay(date: String = Constants.getCurrentDate()): Day?

}