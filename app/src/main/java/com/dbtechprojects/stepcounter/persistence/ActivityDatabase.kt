package com.dbtechprojects.stepcounter.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dbtechprojects.stepcountershared.models.ActivityDao
import com.dbtechprojects.stepcountershared.models.Day

@Database(entities = [
  Day::class], version = 1)
abstract class ActivityDatabase : RoomDatabase() {
    abstract fun ActivityDao(): ActivityDao

}