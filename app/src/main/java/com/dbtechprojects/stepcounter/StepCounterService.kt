package com.dbtechprojects.stepcounter

import android.app.*
import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.IBinder
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.dbtechprojects.stepcounter.models.Day
import com.dbtechprojects.stepcounter.persistence.ActivityDao
import com.dbtechprojects.stepcounter.ui.screens.getValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


class StepCounterService: Service() {

    private lateinit var db: ActivityDao
    private var notification: Notification? = null
    private val mNotificationId = 123
    private var currentDaySteps = 0
    private lateinit var sensorManager : SensorManager
    private lateinit var sensorEventListener: SensorEventListener
    private lateinit var builder: NotificationCompat.Builder

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }



    override fun onCreate() {
        super.onCreate()
        generateForegroundNotification()
        sensorEventListener =  object : SensorEventListener {
            override fun onSensorChanged(event: SensorEvent?) {
                Log.d("viewModel", "${event?.getValue()}")

                if (event != null) {
                    GlobalScope.launch(Dispatchers.IO) {
                        val currentDay = db.getCurrentDay()
                        currentDaySteps = (currentDay?.steps ?: 0) + event.getValue()
                        builder.setContentText("You have done ${currentDaySteps} steps today !")
                        NotificationManagerCompat.from(this@StepCounterService).notify(1, builder.build())
                        if (currentDay == null) {
                            db.insertDay(
                                Day(
                                    steps = event.getValue()
                                )
                            )
                        } else {
                            db.updateDay(currentDay.copy(steps = currentDaySteps))
                        }
                    }
                }
            }

            override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action != null && intent.action.equals(ACTION_STOP)) {
            sensorManager.unregisterListener(sensorEventListener)
            stopForeground(true)
        }

        // init db
        db = StepCounterApp.getDao()

        // register sensor
         sensorManager = StepCounterApp.getApplicationContext()
            .getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val currentSteps: Sensor =
            sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR)
        sensorManager.registerListener( sensorEventListener, currentSteps, Sensor.TYPE_STEP_DETECTOR)

        return START_NOT_STICKY
    }

    private fun generateForegroundNotification() {
        // create channel
        val name: CharSequence = "Step Counter"
        val description = "Step Counter notification"
        val channelId = "channel1"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelId, name, importance)
        channel.description = description

         val notificationManager = getSystemService(
            NotificationManager::class.java
        )
        notificationManager.createNotificationChannel(channel)


        // build notification
        builder = NotificationCompat.Builder(this, channelId)
        builder.setContentTitle("Step Counter is Running")
        builder.setOngoing(true)
        builder.setSmallIcon(R.drawable.run_img)
        val managerCompat = NotificationManagerCompat.from(this)
        notification = builder.build()
        notification!!.flags = Notification.FLAG_ONGOING_EVENT
        managerCompat.notify(1, notification!!)
        startForeground(mNotificationId, notification)

    }

    companion object{
        const val ACTION_STOP =  "${BuildConfig.APPLICATION_ID}.stop"
    }

}



