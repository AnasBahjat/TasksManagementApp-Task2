package com.example.taskmanagerapp.ui.common

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.taskmanagerapp.R
import com.example.taskmanagerapp.ui.emptyActivity.EmptyActivity
import com.example.taskmanagerapp.ui.utils.Constants

class MyNotificationManager(private val context : Context)  {
    private lateinit var notificationManager : NotificationManager

    init {
        createNotificationChannel()
    }
    private fun createNotificationChannel(){
        val channelName = context.getString(R.string.channel_name)
        val channelDescription = context.getString(R.string.cahnnel_description)
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(Constants.CHANNEL_ID,channelName,importance).apply {
            description = channelDescription
        }
        notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    fun createNotification(notificationTitle : String , notificationContent : String) {
        val intent = Intent(context, EmptyActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent: PendingIntent =
            PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val builder = NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.notifications_24)
            .setContentTitle(notificationTitle)
            .setContentText(notificationContent)
            .setStyle(
                NotificationCompat.BigTextStyle()
                    .bigText(notificationContent)
            )
            .setContentIntent(pendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
        notificationManager.notify(1111, builder.build())
    }
}