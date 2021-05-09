package com.example.familylovenotification.model.datastore

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

object LockDataStore {

    private const val TAG: String = "LockDataStore"
    private const val LOCK_DATA_STORE_NAME = "LOCK-DATA-STORE"

    private object PreferencesKeys {
        val LOCK_SCREEN_IMAGE_URI = stringPreferencesKey("LockScreenImageUri")
    }

    private lateinit var lockDataStore: DataStore<Preferences>

    fun lockDataStoreInit(context: Context) {
        lockDataStore =
            context.createDataStore(name = LOCK_DATA_STORE_NAME)
    }

    fun getLockScreenImageUri(): Flow<String> {
        return lockDataStore.data
            .catch { exception ->
                if (exception is IOException) {
                    Log.e(TAG, "Error reading preferences.", exception)
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                preferences[PreferencesKeys.LOCK_SCREEN_IMAGE_URI] ?: ""
            }
    }

    suspend fun setLockScreenImageUri(uri: String) {
        lockDataStore.edit { preferences ->
            preferences[PreferencesKeys.LOCK_SCREEN_IMAGE_URI] = uri
        }
    }
}
