package com.dahyun.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseViewModel : ViewModel() {

    abstract var logTag: String

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }

    protected fun Disposable.addToDisposable() = compositeDisposable.add(this)
}