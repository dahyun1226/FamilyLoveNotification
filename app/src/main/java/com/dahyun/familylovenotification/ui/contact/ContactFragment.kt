package com.dahyun.familylovenotification.ui.contact

import androidx.fragment.app.viewModels
import com.dahyun.base.BaseFragment
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.databinding.FragmentContactBinding
import com.dahyun.familylovenotification.ui.contact.adapter.ContactListAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactFragment : BaseFragment<FragmentContactBinding>(R.layout.fragment_contact) {

    override var logTag: String = "ContactFragment"
    private val contactViewModel by viewModels<ContactViewModel>()
    private val contactListAdapter by lazy { ContactListAdapter() }

    companion object {
        fun getInstance() = ContactFragment()
    }

    override fun initLayout() {
        initRecyclerView()
        super.initLayout()
    }

    private fun initRecyclerView() {
        with(binding) {
            this.viewmodel = contactViewModel
            this.rvContactList.adapter = contactListAdapter
        }
    }
}