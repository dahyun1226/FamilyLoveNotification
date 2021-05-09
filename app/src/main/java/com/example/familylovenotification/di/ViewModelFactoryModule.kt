package com.example.familylovenotification.di

import androidx.lifecycle.ViewModelProvider
import com.example.familylovenotification.ui.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}