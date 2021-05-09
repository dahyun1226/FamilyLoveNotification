package com.example.familylovenotification.ui.main

import android.annotation.SuppressLint
import android.graphics.PorterDuff
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import com.example.familylovenotification.FamilyLoveNotification
import com.example.familylovenotification.R
import com.example.familylovenotification.base.BaseActivity
import com.example.familylovenotification.databinding.ActivityMainBinding
import com.example.familylovenotification.di.DaggerAppComponent
import com.example.familylovenotification.model.datastore.LockDataStore
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.INDICATOR_GRAVITY_TOP
import com.google.android.material.tabs.TabLayoutMediator
import javax.inject.Inject


class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(R.layout.activity_main) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: MainViewModel

    lateinit var component: MainComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        injectInit()
        super.onCreate(savedInstanceState)
    }

    override fun init() {
        viewPagerInit()
        dataStoreInit()
    }

    private fun injectInit() {
        component = DaggerAppComponent.factory().create((application as FamilyLoveNotification))
            .mainComponent()
            .setActivity(this).build()
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
    }

    @SuppressLint("InflateParams")
    private fun viewPagerInit() {
        binding.tlMainTab.run {
            setSelectedTabIndicatorColor(context.getColor(R.color.red))
            setSelectedTabIndicatorGravity(INDICATOR_GRAVITY_TOP)
            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabSelected(tab: TabLayout.Tab) {
                    tab.customView?.findViewById<ImageView>(R.id.icon)?.colorFilter = null
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {
                    tab?.customView?.findViewById<ImageView>(R.id.icon)
                        ?.setColorFilter(context.getColor(R.color.gray), PorterDuff.Mode.SRC_ATOP)
                }

                override fun onTabReselected(tab: TabLayout.Tab?) {
                }
            })
        }
        binding.vpMainPager.run {
            adapter = MainPagerAdapter(activity = this@MainActivity)
        }
        TabLayoutMediator(binding.tlMainTab, binding.vpMainPager) { tab, position ->
            val customTab = layoutInflater.inflate(R.layout.custom_tab, null) as LinearLayout
            val icon = customTab.findViewById<ImageView>(R.id.icon)
            when (position) {
                0 -> icon.setImageResource(R.drawable.ic_settings_black)
                1 -> icon.run {
                    setImageResource(R.drawable.ic_contact_phone)
                    setColorFilter(context.getColor(R.color.gray), PorterDuff.Mode.SRC_ATOP)
                }
            }
            tab.run {
                customView = customTab
            }
        }.attach()
    }

    private fun dataStoreInit() {
        LockDataStore.lockDataStoreInit(this)
    }
}

