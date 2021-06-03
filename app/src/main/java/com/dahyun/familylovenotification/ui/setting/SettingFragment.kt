package com.dahyun.familylovenotification.ui.setting

import android.Manifest
import android.Manifest.permission.SEND_SMS
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.dahyun.base.BaseFragment
import com.dahyun.base.ext.toast
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentSettingBinding
import com.dahyun.familylovenotification.service.LockScreenService
import com.dahyun.familylovenotification.service.ScreenOnCheckService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {

    override var logTag: String = "SettingFragment"

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                showNotificationSwitch()
            } else {
                hideNotificationSwitch()
            }
        }

    private fun showNotificationSwitch() {
        with(binding) {
            swNotificationOnOff.visibility = View.VISIBLE
        }
    }

    private fun hideNotificationSwitch() {
        with(binding) {
            swNotificationOnOff.visibility = View.INVISIBLE
        }
    }

    private fun requestSendSmsPermission() {
        when {
            context?.let {
                ContextCompat.checkSelfPermission(it, SEND_SMS)
            } == PackageManager.PERMISSION_GRANTED -> {
                showNotificationSwitch()
            }
            shouldShowRequestPermissionRationale(SEND_SMS) -> {
                context?.toast(R.string.send_message)
                requestPermissionLauncher.launch(SEND_SMS)
            }
            else -> {
                requestPermissionLauncher.launch(SEND_SMS)
            }
        }
    }

    override fun initLayout() {
        super.initLayout()
        with(binding) {
            swLockScreenOnOff.apply {
                isChecked =
                    isLaunchingService("com.dahyun.familylovenotification.service.LockScreenService")
                setOnCheckedChangeListener { _, isChecked ->
                    when (isChecked) {
                        true -> {
                            context?.startForegroundService(
                                Intent(context, LockScreenService::class.java)
                            )
                        }
                        false -> {
                            context?.stopService(
                                Intent(binding.root.context, LockScreenService::class.java)
                            )
                        }
                    }
                }
                visibility = View.INVISIBLE
            }
            swNotificationOnOff.apply {
                isChecked =
                    isLaunchingService("com.dahyun.familylovenotification.service.ScreenOnCheckService")
                setOnCheckedChangeListener { _, isChecked ->
                    when (isChecked) {
                        true -> {
                            context?.startForegroundService(
                                Intent(context, ScreenOnCheckService::class.java)
                            )
                        }
                        false -> {
                            context?.stopService(
                                Intent(binding.root.context, ScreenOnCheckService::class.java)
                            )
                        }
                    }
                }
            }
        }
        requestSendSmsPermission()
    }

    @Suppress("deprecation")
    private fun isLaunchingService(serviceName: String): Boolean {
        val manager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.getRunningServices(Int.MAX_VALUE).forEach {
            Log.d(logTag, it.service.className)
            if (it.service.className == serviceName) {
                return true
            }
        }
        return false
    }

    companion object {
        fun getFragment() = SettingFragment()
    }
}