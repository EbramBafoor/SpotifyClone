package com.bafoor.spotify.exoPlayer.callBacks

import android.app.Notification
import android.content.Intent
import androidx.core.content.ContextCompat
import com.bafoor.spotify.exoPlayer.MusicService
import com.bafoor.spotify.utilities.Constant.NOTIFICATION_ID
import com.google.android.exoplayer2.ui.PlayerNotificationManager

class MusicPlayerNotificationListener(
    private val musicService: MusicService
) : PlayerNotificationManager.NotificationListener {

    /**
     * dismissedByUser true if :
     * the notification is cancelled because the user dismissed the notification.
     */
    override fun onNotificationCancelled(
        notificationId: Int,
        dismissedByUser: Boolean
    ) {
        super.onNotificationCancelled(notificationId, dismissedByUser)
        musicService.apply {
            stopForeground(true)
            isForegroundService = false
            stopSelf()
        }
    }

    /**
     * (onNotificationPosted)
     * Called each time after the notification has been posted.
     */
    override fun onNotificationPosted(
        notificationId: Int,
        notification: Notification,
        ongoing: Boolean
    ) {
        super.onNotificationPosted(notificationId, notification, ongoing)
        musicService.apply {
            if (ongoing && !isForegroundService) {
                ContextCompat.startForegroundService(
                    this,
                    Intent(applicationContext, this::class.java)
                )
                startForeground(NOTIFICATION_ID, notification)
                isForegroundService = true
            }
        }
    }
}