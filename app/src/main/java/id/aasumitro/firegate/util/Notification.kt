package id.aasumitro.firegate.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import id.aasumitro.firegate.R
import id.aasumitro.firegate.ui.MainActivity

/**
 * Created by A. A. Sumitro on 16/07/19.
 * aasumitro@merahputih.id
 * https://aasumitro.id
 */
private const val NOTIFICATION_CHANNEL_ID = "5440544"
private const val NOTIFICATION_CHANNEL_NAME = "DA_MOVIE"

fun showNotification(
    context: Context?,
    title: String?,
    message: String?,
    notifyId: Int
) {
    val mNotificationManager =
        context?.getSystemService(
            Context.NOTIFICATION_SERVICE
        ) as NotificationManager
    val mIntent =
        Intent(
            context,
            MainActivity::class.java
        )
    val mPendingIntent =
        PendingIntent.getActivity(
            context,
            notifyId,
            mIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    val mAlarmRingtone =
        RingtoneManager.getDefaultUri(
            RingtoneManager.TYPE_NOTIFICATION
        )

    val mBuilder =
        NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_blue_24dp)
            .setContentTitle(title)
            .setContentText(message)
            .setContentIntent(mPendingIntent)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000))
            .setSound(mAlarmRingtone)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val mImportance = NotificationManager.IMPORTANCE_DEFAULT
        val mNotificationChannel =
            NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                mImportance
            )
        mNotificationChannel.enableLights(true)
        mNotificationChannel.lightColor = Color.RED
        mNotificationChannel.enableVibration(true)
        mNotificationChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        mBuilder.setChannelId(NOTIFICATION_CHANNEL_ID)
        mNotificationManager.createNotificationChannel(mNotificationChannel)
    }

    val mNotification = mBuilder.build()
    mNotificationManager.notify(notifyId, mNotification)

}