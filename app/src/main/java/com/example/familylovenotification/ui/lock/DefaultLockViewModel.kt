package com.example.familylovenotification.ui.lock

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.familylovenotification.model.repository.LockScreenRepository
import javax.inject.Inject

class DefaultLockViewModel @Inject constructor(override val lockScreenRepository: LockScreenRepository) : LockViewModel() {

    override lateinit var lockScreenImageUri: LiveData<Uri>

    override fun getLockScreenImageUri() {
        lockScreenImageUri = lockScreenRepository.getLockScreenUri().asLiveData()
    }
}
