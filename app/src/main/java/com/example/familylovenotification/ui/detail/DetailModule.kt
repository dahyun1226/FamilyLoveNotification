package com.example.familylovenotification.ui.detail

import androidx.lifecycle.ViewModelProvider
import dagger.Module
import dagger.Provides

@Module
abstract class DetailModule {
    companion object {
        @Provides
        fun provideDetailViewModel(activity: DetailActivity): DetailViewModel {
            return ViewModelProvider(activity).get(DefaultDetailViewModel::class.java)
        }
    }
}