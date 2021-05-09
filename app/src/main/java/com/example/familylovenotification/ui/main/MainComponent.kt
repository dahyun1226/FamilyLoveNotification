package com.example.familylovenotification.ui.main

import com.example.familylovenotification.ui.main.contact.ContactComponent
import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setActivity(activity: MainActivity): Builder
        fun build(): MainComponent
    }

    fun inject(activity: MainActivity)
    fun contactComponent(): ContactComponent.Builder
}