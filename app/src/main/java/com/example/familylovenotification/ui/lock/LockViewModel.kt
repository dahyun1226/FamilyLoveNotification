package com.example.familylovenotification.ui.lock

import android.net.Uri
import androidx.lifecycle.LiveData
import com.example.familylovenotification.base.BaseViewModel
import com.example.familylovenotification.model.repository.LockScreenRepository

abstract class LockViewModel : BaseViewModel() {
    abstract val lockScreenRepository: LockScreenRepository

    abstract var lockScreenImageUri: LiveData<Uri>

    abstract fun getLockScreenImageUri()
}