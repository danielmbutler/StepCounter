package com.dbtechprojects.stepcountershared.models
import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface ActivityDao {

    @Query("SELECT * FROM Days WHERE date > datetime('now', '-7 day') ORDER BY date desc")
    fun getActivityInLastWeek() : LiveData<List<Day>>

    @Query("SELECT steps FROM Days WHERE date=:date")
    fun getCurrentCount(date: String = getCurrentDate()): LiveData<Int?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDay(day: Day)

    @Update
    fun updateDay(day: Day)

    @Query("SELECT * FROM Days WHERE date=:date")
    fun getCurrentDay(date: String = getCurrentDate()): Day?

}