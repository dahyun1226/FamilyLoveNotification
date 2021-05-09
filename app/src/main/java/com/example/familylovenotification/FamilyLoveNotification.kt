package com.example.familylovenotification

import android.app.Application
import androidx.work.Configuration
import androidx.work.WorkManager
import androidx.work.WorkerFactory
import com.example.familylovenotification.base.ext.registerActivityLifecycleCallback
import com.example.familylovenotification.di.AppComponent
import com.example.familylovenotification.di.DaggerAppComponent
import javax.inject.Inject


class FamilyLoveNotification : Application() {

    @Inject
    lateinit var workerFactory: WorkerFactory

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        registerActivityLifecycleCallback()
        super.onCreate()
        appComponent = DaggerAppComponent.factory().create(applicationContext)
        appComponent.inject(this)
        WorkManager.initialize(
            this,
            Configuration.Builder().setWorkerFactory(workerFactory).build()
        )
    }
}
