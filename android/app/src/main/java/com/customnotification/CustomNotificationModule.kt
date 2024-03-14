package com.customnotification

import android.os.Build
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod


class CustomNotificationModule(reactContext: ReactApplicationContext) :
    ReactContextBaseJavaModule(reactContext) {

    private val customNotificationManager =
        CustomNotificationManager(reactContext.applicationContext)

    override fun getName() = "CustomNotificationModule"

    @RequiresApi(Build.VERSION_CODES.P)
    @ReactMethod
    fun createNotification(title: String, description: String) {

        // Create channel if needed
        customNotificationManager.createChannel(
            "myChannelId",
            "my_custom_channel",
            "Channel id to custom notification"
        )

        // Inflate layout
        val smallNotificationLayout =
            customNotificationManager.inflateLayout(R.layout.custom_small_notification)

        // Update notification layout
        smallNotificationLayout.setTextViewText(R.id.content_title, title)
        smallNotificationLayout.setTextViewText(R.id.content_description, description)
        smallNotificationLayout.setImageViewResource(R.id.content_image, R.mipmap.ic_launcher)

        // Create notification builder
        val builder = customNotificationManager.createBuilder("myChannelId")

        // Apply custom layout
        builder.setCustomContentView(smallNotificationLayout)
        val notification = builder.build()

        // Dispatch notification
        customNotificationManager.dispatch(10, notification)
    }
}