package com.dahyun.familylovenotification.di

import android.content.Context
import com.dahyun.familylovenotification.data.dao.FamilyMemberDao
import com.dahyun.familylovenotification.data.database.FamilyMemberDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideFamilyDatabase(@ApplicationContext context: Context): FamilyMemberDatabase {
        return FamilyMemberDatabase.getInstance(context)
    }

    @Provides
    fun provideFamilyMember(familyMemberDatabase: FamilyMemberDatabase): FamilyMemberDao {
        return familyMemberDatabase.familyMemberDao()
    }
}