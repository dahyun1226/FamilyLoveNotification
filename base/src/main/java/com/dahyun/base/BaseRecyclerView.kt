package com.dahyun.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class Adapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutRes: Int,
        private val bindingVariableId: Int? = null,
        diffCallback: DiffUtil.ItemCallback<ITEM>
    ) : ListAdapter<ITEM, ViewHolder<B>>(diffCallback) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> =
            object : ViewHolder<B>(
                DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    layoutRes,
                    parent,
                    false
                ),
                bindingVariableId = bindingVariableId
            ) {}

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.bind(getItem(position))
        }
    }

    abstract class ViewHolder<B : ViewDataBinding>(
        protected val binding: B,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(binding.root) {

        open fun bind(item: Any) {
            bindingVariableId?.let {
                binding.setVariable(it, item)
            }
        }
    }
}
