package com.dahyun.familylovenotification.ui.contact

import android.content.Intent
import androidx.fragment.app.viewModels
import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentContactBinding
import com.dahyun.familylovenotification.ui.contact.adapter.ContactListAdapter
import com.dahyun.familylovenotification.ui.detail.DetailActivity
import com.dahyun.familylovenotification.ui.detail.DetailActivity.Companion.FAMILY_MEMBER
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact),
    ContactListAdapter.ContactClickListener {

    override var logTag: String = "ContactFragment"
    private val contactViewModel by viewModels<ContactViewModel>()
    private val contactListAdapter by lazy { ContactListAdapter(this) }

    companion object {
        fun getInstance() = ContactFragment()
    }

    override fun initLayout() {
        initRecyclerView()
        super.initLayout()
    }

    override fun onResume() {
        super.onResume()
        contactViewModel.getAllFamilyMembers()
    }

    private fun initRecyclerView() {
        with(binding) {
            this.viewmodel = contactViewModel
            this.rvContactList.adapter = contactListAdapter
        }
    }

    override fun onContactClick(position: Int) {
        Intent(context, DetailActivity::class.java).run {
            contactViewModel.familyMembers.value?.let {
                putExtra(FAMILY_MEMBER, it[position])
            }
            startActivity(this)
        }
    }
}