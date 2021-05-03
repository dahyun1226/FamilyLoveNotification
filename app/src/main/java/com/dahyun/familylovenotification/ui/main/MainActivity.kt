package com.dahyun.familylovenotification.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dahyun.base.BaseActivity
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override var logTag: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}