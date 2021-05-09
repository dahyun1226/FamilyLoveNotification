package com.example.familylovenotification.ui.main.contact

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [ContactModule::class])
interface ContactComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setFragment(fragment: ContactFragment): Builder
        fun create(): ContactComponent
    }

    fun inject(fragment: ContactFragment)
}