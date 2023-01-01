package com.dbtechprojects.stepCounterWatch
import androidx.wear.tiles.*
import androidx.wear.tiles.ColorBuilders.argb
import com.google.common.util.concurrent.Futures


class StepTile : TileService() {
    override fun onTileRequest(requestParams: RequestBuilders.TileRequest) =
        Futures.immediateFuture(
            TileBuilders.Tile.Builder()
            .setResourcesVersion("2")
            .setTimeline(
                TimelineBuilders.Timeline.Builder().addTimelineEntry(
                TimelineBuilders.TimelineEntry.Builder().setLayout(
                    LayoutElementBuilders.Layout.Builder().setRoot(
                        LayoutElementBuilders.Text.Builder().setText("Hello world!").setFontStyle(
                            LayoutElementBuilders.FontStyle.Builder().setColor(ColorBuilders.ColorProp.Builder().setArgb(
                                0xFFFF0000.toInt()
                            ).build()).build()
                        ).build()
                    ).build()
                ).build()
            ).build()
        ).build())

    override fun onResourcesRequest(requestParams: RequestBuilders.ResourcesRequest) =
        Futures.immediateFuture(
            ResourceBuilders.Resources.Builder()
            .setVersion("2")
            .build()
        )
}