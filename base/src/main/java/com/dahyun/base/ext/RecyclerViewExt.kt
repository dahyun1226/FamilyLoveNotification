package com.dahyun.base.ext

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dahyun.base.BaseRecyclerView

@BindingAdapter("replaceList")
fun RecyclerView.replaceList(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (this.adapter as BaseRecyclerView.Adapter<Any, *>).run {
        this.submitList(list)
    }
}

