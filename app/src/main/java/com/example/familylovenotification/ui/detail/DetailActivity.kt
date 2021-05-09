package com.example.familylovenotification.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.familylovenotification.FamilyLoveNotification
import com.example.familylovenotification.R
import com.example.familylovenotification.base.BaseActivity
import com.example.familylovenotification.databinding.ActivityDetailBinding
import com.example.familylovenotification.di.DaggerAppComponent
import com.example.familylovenotification.ui.main.contact.ContactFragment.Companion.ID
import javax.inject.Inject

class DetailActivity :
    BaseActivity<ActivityDetailBinding, DetailViewModel>(R.layout.activity_detail) {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override lateinit var viewModel: DetailViewModel

    var id: Int = 0

    lateinit var component: DetailComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        injectInit()
        super.onCreate(savedInstanceState)

        viewModel.familyMember.observe(this, Observer {
            Log.d("MyTag","it : $it")
        })
    }

    override fun init() {
        id = intent.getIntExtra(ID, 0)
        if (id != 0) {
            viewModel.getFamilyMemberById(id)
        }
        binding.activity = this
    }

    private fun injectInit() {
        component = DaggerAppComponent.factory().create((application as FamilyLoveNotification))
            .detailComponent()
            .setActivity(this).build()
        component.inject(this)
        viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)
    }

    fun save() {
        viewModel.familyMember.value?.let {
            viewModel.insertFamilyMember(it)
        }
        finish()
    }

    fun delete() {
        if (id != 0) {
            viewModel.deleteFamilyMember(id)
        }
        finish()
    }
}