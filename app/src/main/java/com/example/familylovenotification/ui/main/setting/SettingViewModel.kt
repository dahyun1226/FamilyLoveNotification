package com.example.familylovenotification.ui.main.setting

import android.net.Uri
import com.example.familylovenotification.base.BaseViewModel
import com.example.familylovenotification.model.repository.LockScreenRepository

abstract class SettingViewModel : BaseViewModel() {

    abstract val lockScreenRepository: LockScreenRepository

    abstract fun lockScreenImageUriStore(uri: Uri)

}