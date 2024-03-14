package com.customnotification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class CustomNotificationManager(private val context: Context) {

    fun createChannel(chanelId: String, name: String, channelDescription: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(chanelId, name, NotificationManager.IMPORTANCE_HIGH).apply {
                    description = channelDescription
                }
            val notificationManager =
                context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun inflateLayout(layout: Int): RemoteViews {
        return RemoteViews(context.packageName, layout)
    }


    fun createBuilder(chanelId: String): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, chanelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
    }

    @SuppressLint("MissingPermission")
    fun dispatch(id: Int, notification: Notification) {
        NotificationManagerCompat.from(context).notify(id, notification)
    }
}
