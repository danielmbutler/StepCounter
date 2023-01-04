package com.dbtechprojects.stepcountershared.models

import android.app.ActivityManager
import android.app.Application
import android.content.Context
import android.hardware.SensorEvent
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService


fun isServiceRunning(serviceClassName: String?, context: Context): Boolean {
    val activityManager =
        context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    val services: List<ActivityManager.RunningServiceInfo> = activityManager.getRunningServices(Int.MAX_VALUE)
    for (runningServiceInfo in services) {
        if (runningServiceInfo.service.getClassName().equals(serviceClassName)) {
            return true
        }
    }
    return false
}

fun SensorEvent.getValue(): Int {
    Log.d("counto", (this.values[0].toString()))
    return (this.values?.get(0) ?: 0).toInt()
}