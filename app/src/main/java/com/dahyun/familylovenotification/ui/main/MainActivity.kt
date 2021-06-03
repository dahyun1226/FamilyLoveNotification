package com.dahyun.familylovenotification.ui.main

import android.view.MenuItem
import androidx.fragment.app.commit
import com.dahyun.base.BaseActivity
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.ActivityMainBinding
import com.dahyun.familylovenotification.ui.contact.ContactFragment
import com.dahyun.familylovenotification.ui.setting.SettingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override var logTag: String = "MainActivity"

    private val contactFragment by lazy { ContactFragment.getFragment() }
    private val settingFragment by lazy { SettingFragment.getFragment() }

    override fun initLayout() {
        navigateFragment(MainType.SETTING)
        binding.bnvBottomNavigationView.setOnNavigationItemSelectedListener(bottomNavigationListener)
    }

    private val bottomNavigationListener: (menu: MenuItem) -> Boolean = { menu ->
        binding.bniBottomNavigationIndicator
            .selectedItemId = menu.itemId
        when (menu.itemId) {
            R.id.action_setting -> {
                navigateFragment(MainType.SETTING)
                true
            }
            R.id.action_contact -> {
                navigateFragment(MainType.CONTACT)
                true
            }
            else -> false
        }
    }


    private fun navigateFragment(mainType: MainType) {
        val fragment = getFragment(mainType)
        val primaryFragment = supportFragmentManager.primaryNavigationFragment
        supportFragmentManager.commit {
            show(fragment)
            primaryFragment?.let { supportFragmentManager.commit { hide(it) } }
            setPrimaryNavigationFragment(fragment)
        }
    }

    private fun getFragment(mainType: MainType) = when (mainType) {
        MainType.CONTACT -> contactFragment
        MainType.SETTING -> settingFragment
    }.apply {
        if (!isAdded) {
            supportFragmentManager.beginTransaction()
                .add(R.id.fcv_fragment_container, this).commit()
        }
    }
}