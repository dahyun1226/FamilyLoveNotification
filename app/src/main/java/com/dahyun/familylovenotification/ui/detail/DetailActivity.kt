package com.dahyun.familylovenotification.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.core.widget.doOnTextChanged
import com.dahyun.base.BaseActivity
import com.dahyun.familylovenotification.R
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.databinding.ActivityDetailBinding
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : BaseActivity<ActivityDetailBinding>(R.layout.activity_detail) {

    override var logTag: String = "DetailActivity"

    private val detailViewModel by viewModels<DetailViewModel>()

    override fun initLayout() {
        super.initLayout()
        intent.getParcelableExtra<FamilyMember>(FAMILY_MEMBER)?.let {
            detailViewModel.setFamilyMember(it)
        }
        initBinding()
    }

    private fun initBinding() {
        with(binding) {
            this.viewModel = detailViewModel
            this.tvSave.setOnClickListener {
                finish()
            }
            this.swSendMessageOnOff.setOnCheckedChangeListener { _, isChecked ->
                when (isChecked) {
                    false -> swSendMessageOnOff.setOnClickListener {
                        detailViewModel.offSending()
                    }
                    true -> swSendMessageOnOff.setOnClickListener {
                        detailViewModel.onSending()
                    }
                }
            }
            this.etPhoneNumber.apply {
                doOnTextChanged { _ , _, _, _ ->
                    detailViewModel.setPhoneNumber(this.text.toString())
                }
            }
            this.etName.apply {
                doOnTextChanged { _ , _, _, _ ->
                    detailViewModel.setName(this.text.toString())
                }
            }
        }
    }


    companion object {
        const val FAMILY_MEMBER = "family_member"
    }
}