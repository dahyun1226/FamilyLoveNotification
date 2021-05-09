package com.example.familylovenotification.br

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.familylovenotification.ui.lock.LockActivity
import javax.inject.Inject

class ScreenOffReceiver @Inject constructor() : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            Intent.ACTION_SCREEN_OFF -> {
                val intent = Intent(context, LockActivity::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                context?.startActivity(intent)
            }
        }
    }
}