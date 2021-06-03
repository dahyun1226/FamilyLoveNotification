package com.dahyun.familylovenotification.ui.lock

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.dahyun.base.BaseActivity
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.ActivityLockBinding
import com.dahyun.familylovenotification.util.ClickUtil
import com.dahyun.familylovenotification.wm.SendSmsWorker
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LockActivity : BaseActivity<ActivityLockBinding>(R.layout.activity_lock) {
    override var logTag: String = "LockActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val click by lazy { ClickUtil(this.lifecycle) }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                click.run {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                        != PackageManager.PERMISSION_GRANTED
                    ) {
                        requestPermissions(Array(1) { Manifest.permission.SEND_SMS }, 300)
                    } else {
                        val sendSmsWorkRequest: WorkRequest =
                            OneTimeWorkRequestBuilder<SendSmsWorker>()
                                .build()
                        WorkManager
                            .getInstance(this)
                            .enqueue(sendSmsWorkRequest)
                    }
                    finishAndRemoveTask()
                }
            }
            else -> super.onTouchEvent(event)
        }
        return true
    }

}