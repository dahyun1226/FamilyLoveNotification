package com.example.familylovenotification.base

import android.app.Activity
import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.familylovenotification.BR

abstract class BaseActivity<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes
    val layoutRes: Int
) : AppCompatActivity() {
    protected abstract val viewModel: VM
    protected val binding: B by lazy {
        DataBindingUtil.setContentView(this, layoutRes)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.run {
            lifecycleOwner = this@BaseActivity
            setVariable(BR.viewModel, viewModel)
        }
        init()
    }

    abstract fun init()
}
