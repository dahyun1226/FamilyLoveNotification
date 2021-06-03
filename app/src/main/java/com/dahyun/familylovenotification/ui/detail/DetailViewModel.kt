package com.dahyun.familylovenotification.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dahyun.base.BaseViewModel
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.data.repository.FamilyMemberRepository
import com.dahyun.familylovenotification.test.MockFamilyMember
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val familyMemberRepository: FamilyMemberRepository) :
    BaseViewModel() {
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
    }

    fun saveFamilyMember() {
        familyMember.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                familyMemberRepository.insertFamilyMember(it)
            }
        }
    }

    fun deleteFamilyMember() {
        familyMember.value?.let {
            viewModelScope.launch(Dispatchers.IO) {
                familyMemberRepository.deleteFamilyMember(it.id)
            }
        }
    }
}