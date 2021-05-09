package com.example.familylovenotification.ui.main.contact

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.familylovenotification.BR
import com.example.familylovenotification.R
import com.example.familylovenotification.base.BaseFragment
import com.example.familylovenotification.base.BaseRecyclerView
import com.example.familylovenotification.databinding.FragmentContactBinding
import com.example.familylovenotification.databinding.ItemFamilyMemeberBinding
import com.example.familylovenotification.model.data.FamilyMember
import com.example.familylovenotification.ui.detail.DetailActivity
import com.example.familylovenotification.ui.main.MainActivity
import javax.inject.Inject

class ContactFragment :
    BaseFragment<FragmentContactBinding, ContactViewModel>(R.layout.fragment_contact) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: ContactViewModel

    lateinit var component: ContactComponent

    companion object {
        const val ID = "id"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        initBeforeCreate()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onResume() {
        viewModel.getFamilyMembers()
        super.onResume()
    }

    private fun initBeforeCreate() {
        component =
            (activity as MainActivity).component.contactComponent().setFragment(this).create()
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ContactViewModel::class.java)
    }

    override fun init() {
        binding.fragment = this
        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.recyclerView.adapter =
            object : BaseRecyclerView.Adapter<FamilyMember, ItemFamilyMemeberBinding>(
                R.layout.item_family_memeber,
                BR.familymember
            ) {
                override fun onBindViewHolder(
                    holder: BaseRecyclerView.ViewHolder<ItemFamilyMemeberBinding>,
                    position: Int
                ) {
                    super.onBindViewHolder(holder, position)
                    holder.binding.fragment = this@ContactFragment
                }
            }
    }

    fun addNewFamilyMember() {
        val intent = Intent(context, DetailActivity::class.java)
        context?.startActivity(intent)
    }

    fun updateFamilyMember(id: Int) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra(ID, id)
        context?.startActivity(intent)
    }
}
