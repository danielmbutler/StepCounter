package com.dbtechprojects.stepCounterWatch

import android.app.Activity
import android.content.ComponentName
import android.os.Bundle
import android.widget.FrameLayout
import com.dbtechprojects.stepCounterWatch.databinding.ActivityMainBinding

class MainActivity : Activity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val rootLayout = findViewById<FrameLayout>(R.id.tile_container)
//        TileManager(
//            context = this,
//            component = ComponentName(this, StepTile::class.java),
//            parentView = rootLayout
//        ).create()

    }
}