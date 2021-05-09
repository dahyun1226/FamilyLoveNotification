package com.example.familylovenotification.di

import androidx.work.WorkerFactory
import com.example.familylovenotification.workmanager.WorkerManagerFactory
import dagger.Binds
import dagger.Module

@Module
abstract class WorkerFactoryModule {
    @Binds
    abstract fun bindWorkerFactory(workerFactory: WorkerManagerFactory) : WorkerFactory
}