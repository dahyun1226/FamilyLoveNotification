package com.dahyun.familylovenotification.ui.contact.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.dahyun.base.BR
import com.dahyun.base.BaseRecyclerView
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.databinding.ItemContactBinding

class ContactListAdapter(private val contactClickListener: ContactClickListener) :
    BaseRecyclerView.Adapter<FamilyMember, ItemContactBinding>(
        R.layout.item_contact,
        BR.familymember,
        ContactDiffCallback
    ) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseRecyclerView.ViewHolder<ItemContactBinding> {
        return ContactViewHolder(
            ItemContactBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            ), androidx.databinding.library.baseAdapters.BR.familymember,
            contactClickListener
        )
    }

    object ContactDiffCallback : DiffUtil.ItemCallback<FamilyMember>() {
        override fun areItemsTheSame(oldItem: FamilyMember, newItem: FamilyMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FamilyMember, newItem: FamilyMember): Boolean {
            return oldItem == newItem
        }
    }

    interface ContactClickListener {
        fun onContactClick(position: Int)
    }
}