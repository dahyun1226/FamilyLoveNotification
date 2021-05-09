package com.example.familylovenotification.model.repository

import android.net.Uri
import com.example.familylovenotification.model.datastore.LockDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DefaultLockScreenRepository @Inject constructor() : LockScreenRepository {

    override fun getLockScreenUri(): Flow<Uri> {
        return LockDataStore.getLockScreenImageUri().map {
            Uri.parse(it)
        }
    }

    override suspend fun setLockScreenUri(uri: Uri) {
        LockDataStore.setLockScreenImageUri(uri.toString())
    }
}