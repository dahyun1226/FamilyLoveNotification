package com.example.familylovenotification.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView {

    abstract class Adapter<ITEM : Any, B : ViewDataBinding>(
        @LayoutRes private val layoutRes: Int,
        private val bindingVariableId: Int? = null
    ) : RecyclerView.Adapter<ViewHolder<B>>() {

        private val items = mutableListOf<ITEM>()

        fun replaceAll(newItems: List<ITEM>?) {
            newItems?.let {
                items.run {
                    clear()
                    addAll(it)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<B> =
            object : ViewHolder<B>(
                parent = parent,
                layoutRes = layoutRes,
                bindingVariableId = bindingVariableId
            ) {}

        override fun onBindViewHolder(holder: ViewHolder<B>, position: Int) {
            holder.bind(items[position])
        }

        override fun getItemCount(): Int = items.size
    }

    abstract class ViewHolder<B : ViewDataBinding>(
        parent: ViewGroup?,
        @LayoutRes layoutRes: Int,
        private val bindingVariableId: Int?
    ) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent?.context)
            .inflate(layoutRes, parent, false)
    ) {
        val binding: B = DataBindingUtil.bind(itemView)!!

        fun bind(item: Any) {
            bindingVariableId?.let {
                binding.setVariable(it, item)
            }
        }
    }
}

