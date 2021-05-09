package com.example.familylovenotification.ui.lock

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [LockModule::class])
interface LockComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setActivity(activity: LockActivity): Builder
        fun build(): LockComponent
    }

    fun inject(activity: LockActivity)
}