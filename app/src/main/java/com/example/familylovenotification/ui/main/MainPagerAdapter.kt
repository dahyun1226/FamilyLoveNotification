package com.example.familylovenotification.ui.main

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.familylovenotification.ui.main.contact.ContactFragment
import com.example.familylovenotification.ui.main.setting.SettingFragment

class MainPagerAdapter(activity: MainActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> SettingFragment()
            else -> ContactFragment()
        }
    }
}
