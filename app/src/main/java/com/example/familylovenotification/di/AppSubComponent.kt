package com.example.familylovenotification.di

import com.example.familylovenotification.ui.main.MainComponent
import dagger.Module

@Module(
    subcomponents = [
        MainComponent::class,
    ]
)
class AppSubComponents
