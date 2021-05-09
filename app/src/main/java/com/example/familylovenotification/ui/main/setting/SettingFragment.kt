package com.example.familylovenotification.ui.main.setting

import android.content.Intent
import android.net.Uri
import android.widget.Switch
import androidx.activity.result.contract.ActivityResultContracts
import com.example.familylovenotification.R
import com.example.familylovenotification.base.BaseFragment
import com.example.familylovenotification.databinding.FragmentSettingBinding
import com.example.familylovenotification.service.LockScreenService

class SettingFragment :
    BaseFragment<FragmentSettingBinding, SettingViewModel>(R.layout.fragment_setting) {
    override val viewModel: SettingViewModel = DefaultSettingViewModel()

    private val getPhoto =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                viewModel.lockScreenImageUriStore(it)
            }
        }

    override fun init() {
        binding.root.findViewById<Switch>(R.id.sw_lockScreenOnOff)
            .setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    false -> {
                        context?.stopService(
                            Intent(
                                binding.root.context,
                                LockScreenService::class.java
                            )
                        )
                    }
                    true -> {
                        context?.startForegroundService(
                            Intent(
                                binding.root.context,
                                LockScreenService::class.java
                            )
                        )
                    }
                }
            }

        binding.ivChangeLockScreen.setOnClickListener {
            setAnotherPhoto()
        }
    }

    private fun setAnotherPhoto() {
        getPhoto.launch("image/*")
    }
}