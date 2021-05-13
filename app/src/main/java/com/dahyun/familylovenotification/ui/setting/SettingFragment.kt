package com.dahyun.familylovenotification.ui.setting

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentSettingBinding
import com.dahyun.familylovenotification.service.LockScreenService
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingFragment : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override var logTag: String = "SettingFragment"

    override fun initLayout() {
        super.initLayout()
        binding.swLockScreenOnOff.apply {
            isChecked = isLaunchingService()
            setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    true -> {
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                            context?.startForegroundService(
                                Intent(context, LockScreenService::class.java)
                            )
                        }
                    }
                    false -> {
                        context?.stopService(
                            Intent(binding.root.context, LockScreenService::class.java)
                        )
                    }
                }
            }
        }
    }

    companion object {
        fun getInstance() = SettingFragment()
    }

    @Suppress("deprecation")
    private fun isLaunchingService(): Boolean {
        val manager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        manager.getRunningServices(Int.MAX_VALUE).forEach {
            Log.d(logTag,it.service.className)
            if (it.service.className == "com.dahyun.familylovenotification.service.LockScreenService") {
                return true
            }
        }
        return false
    }
}