package com.example.familylovenotification.ui.main.contact

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.familylovenotification.model.data.FamilyMember
import com.example.familylovenotification.model.repository.FamilyMemberRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DefaultContactViewModel @Inject constructor(val familyMemberRepository: FamilyMemberRepository) :
    ContactViewModel() {

    private val _familyMembers = MutableLiveData<List<FamilyMember>>()
    override val familyMembers: LiveData<List<FamilyMember>> = _familyMembers

    override fun getFamilyMembers() {
        viewModelScope.launch(Dispatchers.IO) {
            _familyMembers.postValue(
                familyMemberRepository.getAllFamilyMember()
            )
        }
    }
}