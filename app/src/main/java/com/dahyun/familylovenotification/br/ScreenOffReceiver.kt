package com.dahyun.familylovenotification.br

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.dahyun.familylovenotification.service.LockScreenService
import com.dahyun.familylovenotification.ui.lock.LockActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScreenOffReceiver @Inject constructor(private val lockScreenService: LockScreenService) :
    BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_OFF -> {
                Intent(context, LockActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP)
                }.run {
                    PendingIntent.getActivity(lockScreenService,200,this,PendingIntent.FLAG_CANCEL_CURRENT).send()
                }
            }
        }
    }
}