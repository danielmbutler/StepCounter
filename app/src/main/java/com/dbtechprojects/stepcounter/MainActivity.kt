package com.dbtechprojects.stepcounter

import android.Manifest
import android.content.Context
import android.hardware.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import com.dbtechprojects.stepcounter.ui.theme.StepCounterTheme

class MainActivity : ComponentActivity(), SensorEventListener {

    private var count = mutableStateOf(0)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val requestPermissionLauncher =
            registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted: Boolean ->
                if (isGranted) {
                    val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
                    val currentSteps: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER)
                    sensorManager.registerListener(this, currentSteps,Sensor.TYPE_STEP_COUNTER)

                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                }
            }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        }

        setContent {
            StepCounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    Greeting("Daniel", count)
                }
            }
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_STEP_COUNTER){
            count.value = event.getValue()
        }
        Log.d("event", event.toString())

        Log.d("event23", "values ${event?.values}")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}

@Composable
fun Greeting(name: String, steps: MutableState<Int>) {
    Text(text = "Hello $name you have done ${steps.value} steps today!")
}

fun SensorEvent.getValue(): Int {
    return (this.values?.get(0) ?: 0).toInt()
}