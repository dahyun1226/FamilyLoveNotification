package com.dahyun.base.ext

import androidx.databinding.BindingAdapter
import androidx.viewpager2.widget.ViewPager2
import com.dahyun.base.BaseRecyclerView

@BindingAdapter("viewPagerReplaceList")
fun ViewPager2.replaceList(list: List<Any>?) {
    @Suppress("UNCHECKED_CAST")
    (this.adapter as BaseRecyclerView.Adapter<Any, *>).run {
        this.submitList(list)
    }
}