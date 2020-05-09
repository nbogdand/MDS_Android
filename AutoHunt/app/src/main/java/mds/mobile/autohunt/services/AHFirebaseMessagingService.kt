package mds.mobile.autohunt.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import mds.mobile.autohunt.R

class AHFirebaseMessagingService: FirebaseMessagingService() {

    companion object{
        const val NOTIFICATION_CHANNEL_ID = "AUTOHUNT_ID"
        const val NOTIFICATION_CHANNEL_NAME = "AUTOHUNT_NAME"
        const val PENDING_INTENT_REQUEST_CODE = 1
        const val OPEN_NOTIFICATIONS_PAGE = "OPEN_NOTIFICATIONS_PAGE"
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        remoteMessage.notification?.run {
            buildForegroundNotification(this)
        }
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)

    }

    private fun buildForegroundNotification(notification: RemoteMessage.Notification) {
        val title = notification.title
        val content = notification.body

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                NOTIFICATION_CHANNEL_ID,
                NOTIFICATION_CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager.createNotificationChannel(notificationChannel)
        }

        val notificationBuilder = NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
        notificationBuilder.setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_notification)
            .setWhen(System.currentTimeMillis())
            .setContentTitle(title)
            .setContentText(content).priority = NotificationCompat.PRIORITY_HIGH

        notificationManager.notify(1, notificationBuilder.build())
    }
}