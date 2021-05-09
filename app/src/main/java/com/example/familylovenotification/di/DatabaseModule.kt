package com.example.familylovenotification.di

import android.content.Context
import com.example.familylovenotification.model.dao.FamilyMemberDao
import com.example.familylovenotification.model.database.FamilyMemberDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class DatabaseModule {

    companion object {
        @Singleton
        @Provides
        fun providesFamilyMemberDatabase(context: Context): FamilyMemberDatabase =
            FamilyMemberDatabase.getInstance(context)

        @Singleton
        @Provides
        fun providesFamilyMemberDao(db: FamilyMemberDatabase): FamilyMemberDao = db.familyMemberDao()
    }
}