package com.example.familylovenotification.ui.binding

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.familylovenotification.base.BaseRecyclerView

@BindingAdapter("replaceAll")
fun RecyclerView.replaceAll(list: List<Any>?) {
    Log.e("!", "when replace" + list.toString())
    @Suppress("UNCHECKED_CAST")
    (this.adapter as BaseRecyclerView.Adapter<Any, *>).run {
        this.replaceAll(list)
        notifyDataSetChanged()
    }
}
