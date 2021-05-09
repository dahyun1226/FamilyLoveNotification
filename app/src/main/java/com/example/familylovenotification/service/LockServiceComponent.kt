package com.example.familylovenotification.service

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [LockServiceModule::class])
interface LockServiceComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setService(service: LockScreenService): Builder
        fun build(): LockServiceComponent
    }

    fun inject(service: LockScreenService)
}