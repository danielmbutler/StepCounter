package com.dbtechprojects.stepcounter

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.dbtechprojects.stepcountershared.models.ActivityDao
import com.dbtechprojects.stepcounter.persistence.ActivityDatabase

class StepCounterApp : Application(){

    private var db : ActivityDatabase? = null


    init {
        instance = this
    }

    private fun getDb(): ActivityDatabase {
        return if (db != null){
            db!!
        } else {
            db = Room.databaseBuilder(
                instance!!.applicationContext,
                ActivityDatabase::class.java, "Activity"
            ).fallbackToDestructiveMigration()// remove in prod
                .build()
            db!!
        }
    }


    companion object {
        private var instance: StepCounterApp? = null

        fun getDao(): ActivityDao {
            return instance!!.getDb().ActivityDao()
        }

        fun getApplicationContext(): Context {
            return instance!!.applicationContext
        }

    }


}