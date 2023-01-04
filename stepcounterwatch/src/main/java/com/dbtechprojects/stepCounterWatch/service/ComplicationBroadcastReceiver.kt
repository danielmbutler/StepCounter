package com.dbtechprojects.stepCounterWatch.service
import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceUpdateRequester


class ComplicationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val extras = intent.extras ?: return
        val complicationId = extras.getInt(EXTRA_COMPLICATION_ID)
            val complicationDataSourceUpdateRequester =
                ComplicationDataSourceUpdateRequester.create(
                    context = context,
                    complicationDataSourceComponent = ComponentName(context, ComplicationService::class.java)
                )
            complicationDataSourceUpdateRequester.requestUpdate(complicationId)

    }

    companion object{
        private const val EXTRA_COMPLICATION_ID =
            "com.example.android.wearable.complicationsdatasource.action.COMPLICATION_ID"
    }
}