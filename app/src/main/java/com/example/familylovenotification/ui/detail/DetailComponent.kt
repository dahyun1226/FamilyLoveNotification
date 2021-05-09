package com.example.familylovenotification.ui.detail

import dagger.BindsInstance
import dagger.Subcomponent

@Subcomponent(modules = [DetailModule::class])
interface DetailComponent {
    @Subcomponent.Builder
    interface Builder {
        @BindsInstance
        fun setActivity(activity: DetailActivity): Builder
        fun build(): DetailComponent
    }

    fun inject(activity: DetailActivity)
}