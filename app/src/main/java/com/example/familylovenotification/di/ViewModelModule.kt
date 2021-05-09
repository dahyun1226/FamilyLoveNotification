package com.example.familylovenotification.di

import androidx.lifecycle.ViewModel
import com.example.familylovenotification.model.repository.FamilyMemberRepository
import com.example.familylovenotification.model.repository.LockScreenRepository
import com.example.familylovenotification.ui.detail.DefaultDetailViewModel
import com.example.familylovenotification.ui.detail.DetailViewModel
import com.example.familylovenotification.ui.lock.DefaultLockViewModel
import com.example.familylovenotification.ui.lock.LockViewModel
import com.example.familylovenotification.ui.main.DefaultMainViewModel
import com.example.familylovenotification.ui.main.MainViewModel
import com.example.familylovenotification.ui.main.contact.ContactViewModel
import com.example.familylovenotification.ui.main.contact.DefaultContactViewModel
import com.example.familylovenotification.ui.viewmodel.ViewModelKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {
    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(): ViewModel {
        return DefaultMainViewModel()
    }

    @Provides
    @IntoMap
    @ViewModelKey(ContactViewModel::class)
    fun provideContactViewModel(familyMemberRepository: FamilyMemberRepository): ViewModel {
        return DefaultContactViewModel(familyMemberRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(DetailViewModel::class)
    fun provideDetailViewModel(familyMemberRepository: FamilyMemberRepository): ViewModel {
        return DefaultDetailViewModel(familyMemberRepository)
    }

    @Provides
    @IntoMap
    @ViewModelKey(LockViewModel::class)
    fun provideLockViewModel(lockScreenRepository: LockScreenRepository): ViewModel {
        return DefaultLockViewModel(lockScreenRepository)
    }
}
