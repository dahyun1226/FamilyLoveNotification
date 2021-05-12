package com.dahyun.familylovenotification.ui.contact

import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentContactBinding
import com.dahyun.familylovenotification.ui.setting.SettingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact) {
    override var logTag: String = "ContactFragment"

    companion object{
        fun getInstance() = ContactFragment()
    }
}