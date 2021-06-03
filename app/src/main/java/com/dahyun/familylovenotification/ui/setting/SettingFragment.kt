package com.dahyun.familylovenotification.ui.setting

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentSettingBinding
import com.dahyun.familylovenotification.service.LockScreenService
import com.dahyun.familylovenotification.service.ScreenOnCheckService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override var logTag: String = "SettingFragment"

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
    }

    companion object {
        fun getFragment() = SettingFragment()
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
}