package com.dahyun.familylovenotification.ui.contact.adapter

import android.view.View
import androidx.databinding.BindingAdapter
import com.dahyun.base.BaseRecyclerView
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.databinding.ItemContactBinding

class ContactViewHolder(
    binding: ItemContactBinding,
    bindingVariableId: Int?,
    private val contactClickListener: ContactListAdapter.ContactClickListener
) : BaseRecyclerView.ViewHolder<ItemContactBinding>(
    binding,
    bindingVariableId
) {
    init {
        binding.ivEditFamilyMember.setOnClickListener {
            contactClickListener.onContactClick(
                bindingAdapterPosition
            )
        }
    }
}