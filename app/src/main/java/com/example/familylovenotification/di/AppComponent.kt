package com.example.familylovenotification.di

import android.content.Context
import com.example.familylovenotification.FamilyLoveNotification
import com.example.familylovenotification.service.LockServiceComponent
import com.example.familylovenotification.ui.detail.DetailComponent
import com.example.familylovenotification.ui.lock.LockComponent
import com.example.familylovenotification.ui.main.MainComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [DatabaseModule::class,
        AppSubComponents::class,
        RepositoryModule::class,
        ViewModelFactoryModule::class,
        ViewModelModule::class,
        WorkerFactoryModule::class,
        WorkerModule::class]
)
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }

    fun mainComponent(): MainComponent.Builder
    fun detailComponent(): DetailComponent.Builder
    fun lockServiceComponent(): LockServiceComponent.Builder
    fun lockComponent(): LockComponent.Builder
    fun inject(application: FamilyLoveNotification)
}
