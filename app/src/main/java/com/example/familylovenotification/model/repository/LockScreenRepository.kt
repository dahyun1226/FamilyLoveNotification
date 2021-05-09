package com.example.familylovenotification.model.repository

import android.net.Uri
import kotlinx.coroutines.flow.Flow

interface LockScreenRepository {
    fun getLockScreenUri(): Flow<Uri>
    suspend fun setLockScreenUri(uri: Uri)
}