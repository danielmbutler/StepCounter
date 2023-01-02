package com.dbtechprojects.stepCounterWatch


import android.graphics.drawable.Icon
import android.util.Log
import androidx.wear.watchface.complications.data.*
import androidx.wear.watchface.complications.datasource.ComplicationDataSourceService
import androidx.wear.watchface.complications.datasource.ComplicationRequest


class ComplicationService : ComplicationDataSourceService() {


    override fun onComplicationActivated(
        complicationInstanceId: Int,
        type: ComplicationType
    ) {
        Log.d(TAG, "onComplicationActivated(): $complicationInstanceId")
    }


    override fun getPreviewData(type: ComplicationType): ComplicationData {
        return ShortTextComplicationData.Builder(
            text = PlainComplicationText.Builder(text = "1000").build(),
            contentDescription = PlainComplicationText.Builder(text = "Short Text version of Number.").build()
        )
            .setTapAction(null)
            .build()
    }


    override  fun onComplicationRequest(
        request: ComplicationRequest,
        listener: ComplicationRequestListener
    ) {
        Log.d(TAG, "onComplicationRequest() id: ${request.complicationInstanceId}")
        Log.d(TAG, "onComplicationRequest() id: ${request.complicationType}")


         when (request.complicationType) {

            ComplicationType.SHORT_TEXT -> {
                val image: MonochromaticImage = MonochromaticImage.Builder(
                    Icon.createWithResource(applicationContext, com.dbtechprojects.stepcountershared.R.drawable.run_img_white)
                ).build()

               val data =  ShortTextComplicationData.Builder(
                    text = PlainComplicationText.Builder(text = "50").build(),
                    contentDescription = PlainComplicationText
                        .Builder(text = "Short Text version of Number.").build(),
               ).setMonochromaticImage(image)
                    .build()
                listener.onComplicationData(data)
            }

            else -> {
                if (Log.isLoggable(TAG, Log.WARN)) {
                    Log.w(TAG, "Unexpected complication type ${request.complicationType}")
                }
            }
        }
    }


    override fun onComplicationDeactivated(complicationInstanceId: Int) {
        Log.d(TAG, "onComplicationDeactivated(): $complicationInstanceId")
    }


    companion object {
        private const val TAG = "CompDataSourceService"
    }
}