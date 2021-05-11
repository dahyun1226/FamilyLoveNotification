package com.dahyun.familylovenotification.ui.setting

import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentContactBinding
import com.dahyun.familylovenotification.databinding.FragmentSettingBinding

class SettingFragment() : BaseFragment<FragmentSettingBinding>(R.layout.fragment_setting) {
    override var logTag: String = "SettingFragment"

    companion object{
        fun getInstance() = SettingFragment()
    }
}