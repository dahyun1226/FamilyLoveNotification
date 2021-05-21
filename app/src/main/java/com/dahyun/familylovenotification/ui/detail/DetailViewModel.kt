package com.dahyun.familylovenotification.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dahyun.base.BaseViewModel
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.test.MockFamilyMember
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor() : BaseViewModel() {
    override var logTag: String = "DetailViewModel"

    private val _familyMember = MutableLiveData<FamilyMember>()
    val familyMember: LiveData<FamilyMember> = _familyMember

    fun setFamilyMember(familyMember: FamilyMember) {
        _familyMember.value = familyMember
    }

    fun onSending() {
        _familyMember.value = _familyMember.value?.copy(isSendingMessage = true)
    }

    fun offSending() {
        _familyMember.value = _familyMember.value?.copy(isSendingMessage = false)
    }

    fun setName(name: String) {
        _familyMember.value = _familyMember.value?.copy(name = name)
    }

    fun setPhoneNumber(phoneNumber: String) {
        _familyMember.value = _familyMember.value?.copy(phoneNumber = phoneNumber)
        Log.d(logTag,_familyMember.toString())
    }

}