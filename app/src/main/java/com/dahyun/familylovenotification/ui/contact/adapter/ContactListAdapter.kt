package com.dahyun.familylovenotification.ui.contact.adapter

import androidx.recyclerview.widget.DiffUtil
import com.dahyun.base.BR
import com.dahyun.base.BaseRecyclerView
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.databinding.ItemContactBinding

class ContactListAdapter : BaseRecyclerView.Adapter<FamilyMember, ItemContactBinding>(
    R.layout.item_contact,
    BR.familymember,
    ContactDiffCallback
) {
    object ContactDiffCallback : DiffUtil.ItemCallback<FamilyMember>() {
        override fun areItemsTheSame(oldItem: FamilyMember, newItem: FamilyMember): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: FamilyMember, newItem: FamilyMember): Boolean {
            return oldItem == newItem
        }
    }
}