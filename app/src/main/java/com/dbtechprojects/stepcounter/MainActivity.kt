package com.dbtechprojects.stepcounter

import android.Manifest
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dbtechprojects.stepcountershared.models.Day
import com.dbtechprojects.stepcounter.services.StepCounterService
import com.dbtechprojects.stepcounter.ui.screens.HistoryScreen
import com.dbtechprojects.stepcounter.ui.screens.HomeScreen
import com.dbtechprojects.stepcounter.ui.theme.StepCounterTheme
import com.dbtechprojects.stepcountershared.models.MainViewModel

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: MainViewModel
    private var count = mutableStateOf<Int?>(null)
    private var activityLast7Days = listOf<Day>()
    private var showPermDeniedScreen = mutableStateOf(false)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = MainViewModel(StepCounterApp.getDao())
        observeViewModel()

        setContent {
            StepCounterTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Constants.HOME_SCREEN
                    ) {
                        // Home Screen
                        composable(Constants.HOME_SCREEN) {
                            HomeScreen(
                                count = count,
                                runImgContentDescription = getString(R.string.running_img),
                                navController,
                                showPermDeniedScreen
                            )
                        }
                        // History Screen
                        composable(Constants.HISTORY_SCREEN) {
                            HistoryScreen(activityLast7Days)
                        }
                    }

                }
            }
        }
    }

    private fun observeViewModel(){
        viewModel.count.observe(this){ count ->
            this.count.value = count
        }
        viewModel.activityInLastWeek.observe(this){ value ->
            this.activityLast7Days = value
        }
    }


    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                startStepCounterService()
                showPermDeniedScreen.value = false
            } else {
                showPermDeniedScreen.value = true
            }
        }

    private fun startStepCounterService(){
        startForegroundService(Intent(this, StepCounterService::class.java))
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            requestPermissionLauncher.launch(Manifest.permission.ACTIVITY_RECOGNITION)
        } else {
            startStepCounterService()
        }
    }
}

