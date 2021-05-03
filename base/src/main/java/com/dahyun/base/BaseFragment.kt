package com.dahyun.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    abstract var logTag: String

    private var _binding: T? = null
    protected val binding
        get() = _binding!!

    private val compositeDisposable by lazy { CompositeDisposable() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initLayout()
        observeViewModel()
    }

    open fun initLayout() {}

    open fun observeViewModel() {}

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        compositeDisposable.clear()
    }

    protected fun Disposable.addToDisposable() = compositeDisposable.add(this)

}