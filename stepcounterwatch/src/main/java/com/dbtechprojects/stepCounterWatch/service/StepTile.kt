package com.dbtechprojects.stepCounterWatch.service


import android.util.Log
import androidx.wear.tiles.*
import androidx.wear.tiles.DimensionBuilders.dp
import androidx.wear.tiles.DimensionBuilders.expand
import com.dbtechprojects.stepCounterWatch.StepCounterApp
import com.google.common.util.concurrent.Futures
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class StepTile : TileService() {


    override fun onTileRequest(requestParams: RequestBuilders.TileRequest) =
        Futures.immediateFuture(
            TileBuilders.Tile.Builder()
                .setFreshnessIntervalMillis(20000) // 20 secs
                .setResourcesVersion("2")
                .setTimeline(
//                    // IMAGE
                   TimelineBuilders.Timeline.Builder().addTimelineEntry(
                        TimelineBuilders.TimelineEntry.Builder().setLayout(
                            LayoutElementBuilders.Layout.Builder().setRoot(
                            tileLayout(StepCounterApp.getDao().getCurrentCountValue())
                        ).build()
                    ).build()
                ).build()
                ).build()
        )



    override fun onResourcesRequest(requestParams: RequestBuilders.ResourcesRequest) =
        Futures.immediateFuture(
            ResourceBuilders.Resources.Builder()
                .addIdToImageMapping(
                    "run_img", ResourceBuilders.ImageResource.Builder()
                        .setAndroidResourceByResId(
                            ResourceBuilders.AndroidImageResourceByResId.Builder()
                            .setResourceId(com.dbtechprojects.stepcountershared.R.drawable.run_img)
                            .build()
                        ).build()
                )
                .setVersion("2")
                .build()
        )

    private fun tileLayout(count: Int): LayoutElementBuilders.LayoutElement {

        return LayoutElementBuilders.Column.Builder()
            .setWidth(expand())
            .setHeight(expand())
            .addContent(
                LayoutElementBuilders.Image.Builder()
                    .setResourceId("run_img")
                    .setWidth(dp(120f))
                    .setHeight(dp(120f))
                    .build()
            )
            .addContent(
                LayoutElementBuilders.Text.Builder()
                    .setText("$count Steps")
                    .build()
            ).build()
    }


}


