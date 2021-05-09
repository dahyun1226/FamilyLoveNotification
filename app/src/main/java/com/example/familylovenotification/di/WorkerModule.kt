package com.example.familylovenotification.di

import com.example.familylovenotification.workmanager.ChildWorkerFactory
import com.example.familylovenotification.workmanager.SendSmsWorker
import com.example.familylovenotification.workmanager.WorkerKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap


@Module
abstract class WorkerModule {
    @Binds
    @IntoMap
    @WorkerKey(SendSmsWorker::class)
    abstract fun bindSendSmsFactoryWorker(factory: SendSmsWorker.Factory): ChildWorkerFactory
}
