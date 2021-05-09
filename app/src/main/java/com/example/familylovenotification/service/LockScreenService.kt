package com.example.familylovenotification.service

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.IBinder
import com.example.familylovenotification.FamilyLoveNotification
import com.example.familylovenotification.R
import com.example.familylovenotification.br.ScreenOffReceiver
import com.example.familylovenotification.di.DaggerAppComponent
import javax.inject.Inject


class LockScreenService :
    Service() {

    @Inject
    lateinit var screenOffReceiver: ScreenOffReceiver

    private lateinit var component: LockServiceComponent

    private fun injectInit() {
        component = DaggerAppComponent.factory().create((application as FamilyLoveNotification))
            .lockServiceComponent()
            .setService(this).build()
        component.inject(this)
    }

    override fun onCreate() {
        injectInit()
        super.onCreate()
        val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
        registerReceiver(screenOffReceiver, filter)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        super.onStartCommand(intent, flags, startId)
        if (intent != null) {
            val filter = IntentFilter(Intent.ACTION_SCREEN_OFF)
            registerReceiver(screenOffReceiver, filter)
        }
        val chan = NotificationChannel(
            ANDROID_CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_NONE
        )

        val manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(chan)

        val builder = Notification.Builder(this, ANDROID_CHANNEL_ID)
            .setContentTitle(getString(R.string.app_name))
        val notification = builder.build()

        startForeground(NOTIFICATION_ID, notification)

        return START_REDELIVER_INTENT
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(screenOffReceiver)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    companion object {
        const val ANDROID_CHANNEL_ID = "familylovenotification"
        const val NOTIFICATION_ID = 9999
        const val CHANNEL_NAME = "LockScreenService"
    }
}