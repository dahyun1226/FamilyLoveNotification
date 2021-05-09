package com.example.familylovenotification.di

import com.example.familylovenotification.model.repository.DefaultFamilyMemberRepository
import com.example.familylovenotification.model.repository.DefaultLockScreenRepository
import com.example.familylovenotification.model.repository.FamilyMemberRepository
import com.example.familylovenotification.model.repository.LockScreenRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun provideFamilyMemberRepository(familyMemberRepository: DefaultFamilyMemberRepository): FamilyMemberRepository

    @Singleton
    @Binds
    abstract fun provideLockScreenRepository(
        lockScreenRepository: DefaultLockScreenRepository
    ): LockScreenRepository
}