package com.dahyun.familylovenotification.di

import com.dahyun.familylovenotification.data.repository.DefaultFamilyMemberRepository
import com.dahyun.familylovenotification.data.repository.FamilyMemberRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFamilyMemberRepository(
        defaultFamilyMemberRepository: DefaultFamilyMemberRepository
    ): FamilyMemberRepository
}