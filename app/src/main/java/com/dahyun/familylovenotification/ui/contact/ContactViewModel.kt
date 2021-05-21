package com.dahyun.familylovenotification.ui.contact

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
class ContactViewModel @Inject constructor(private val familyMemberRepository: FamilyMemberRepository) :
    BaseViewModel() {
    override var logTag: String = "ContactViewModel"

    private val _familyMembers = MutableLiveData<List<FamilyMember>>()
    val familyMembers: LiveData<List<FamilyMember>> = _familyMembers

    fun getAllFamilyMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            _familyMembers.postValue(familyMemberRepository.getAllFamilyMember())
        }
    }
}
