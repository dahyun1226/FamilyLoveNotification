package com.example.familylovenotification.ui.main.setting

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.familylovenotification.model.repository.DefaultLockScreenRepository
import com.example.familylovenotification.model.repository.LockScreenRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DefaultSettingViewModel : SettingViewModel() {

    override val lockScreenRepository: LockScreenRepository = DefaultLockScreenRepository()

    override fun lockScreenImageUriStore(uri: Uri) {
        viewModelScope.launch(Dispatchers.IO) {
            lockScreenRepository.setLockScreenUri(uri)
        }
    }
}