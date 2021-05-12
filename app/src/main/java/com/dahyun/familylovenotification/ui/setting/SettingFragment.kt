package com.dahyun.familylovenotification.ui.setting

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Switch
import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentContactBinding
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

    private fun isLaunchingService(): Boolean {
//        val manager = context?.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        manager.g
        return false
    }
}