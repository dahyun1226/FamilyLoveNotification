package com.dahyun.familylovenotification.br

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.dahyun.familylovenotification.service.LockScreenService
import com.dahyun.familylovenotification.service.ScreenOnCheckService
import com.dahyun.familylovenotification.ui.lock.LockActivity
import com.dahyun.familylovenotification.wm.SendSmsWorker
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SendSmsReceiver @Inject constructor(private val screenOnCheckService: ScreenOnCheckService) :
    BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_ON -> {
                val sendSmsWorkRequest: WorkRequest =
                    OneTimeWorkRequestBuilder<SendSmsWorker>()
                        .build()
                WorkManager
                    .getInstance(screenOnCheckService)
                    .enqueue(sendSmsWorkRequest)
            }
        }
    }
}