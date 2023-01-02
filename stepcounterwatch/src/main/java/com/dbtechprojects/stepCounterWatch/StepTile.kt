package com.dbtechprojects.stepCounterWatch


import androidx.wear.tiles.*
import androidx.wear.tiles.DimensionBuilders.dp
import androidx.wear.tiles.DimensionBuilders.expand
import com.google.common.util.concurrent.Futures



class StepTile : TileService() {


    override fun onTileRequest(requestParams: RequestBuilders.TileRequest) =
        Futures.immediateFuture(
            TileBuilders.Tile.Builder()
                .setResourcesVersion("2")
                .setTimeline(
//                    // IMAGE
                   TimelineBuilders.Timeline.Builder().addTimelineEntry(
                        TimelineBuilders.TimelineEntry.Builder().setLayout(
                            LayoutElementBuilders.Layout.Builder().setRoot(
                            tileLayout()
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

    private fun tileLayout(): LayoutElementBuilders.LayoutElement =
        LayoutElementBuilders.Column.Builder()
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
                    .setText("50 Steps")
                    .build()
            ).build()

}


