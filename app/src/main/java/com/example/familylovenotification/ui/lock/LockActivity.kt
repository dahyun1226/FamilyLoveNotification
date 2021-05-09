package com.example.familylovenotification.ui.lock

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.MotionEvent
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkRequest
import com.example.familylovenotification.FamilyLoveNotification
import com.example.familylovenotification.R
import com.example.familylovenotification.base.BaseActivity
import com.example.familylovenotification.databinding.ActivityLockBinding
import com.example.familylovenotification.di.DaggerAppComponent
import com.example.familylovenotification.workmanager.SendSmsWorker
import javax.inject.Inject

class LockActivity : BaseActivity<ActivityLockBinding, LockViewModel>(R.layout.activity_lock) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: LockViewModel

    lateinit var component: LockComponent

    @Volatile
    var actionCount = 0

    private fun injectInit() {
        component = DaggerAppComponent.factory().create((application as FamilyLoveNotification))
            .lockComponent()
            .setActivity(this).build()
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(LockViewModel::class.java)
    }

    override fun init() {
        viewModel.getLockScreenImageUri()
        viewModel.lockScreenImageUri.observe(this, {
            binding.ivLockscreenImage.setImageURI(it)
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        injectInit()
        super.onCreate(savedInstanceState)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        return when (event.action) {
            MotionEvent.ACTION_MOVE -> {
                if (ContextCompat.checkSelfPermission(
                        this,
                        Manifest.permission.SEND_SMS
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    requestPermissions(
                        Array(1) { Manifest.permission.SEND_SMS },
                        300
                    )
                } else {
                    synchronized(actionCount) {
                        if (actionCount == 0) {

                            val sendSmsWorkRequest: WorkRequest =
                                OneTimeWorkRequestBuilder<SendSmsWorker>()
                                    .build()
                            WorkManager
                                .getInstance(this)
                                .enqueue(sendSmsWorkRequest)
                            actionCount++
                        }
                    }
                }
                finish()
                true
            }
            else -> super.onTouchEvent(event)
        }
    }
}


