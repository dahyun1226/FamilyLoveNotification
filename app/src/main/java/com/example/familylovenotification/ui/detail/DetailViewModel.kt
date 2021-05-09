package com.example.familylovenotification.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.familylovenotification.base.BaseViewModel
import com.example.familylovenotification.model.data.FamilyMember

abstract class DetailViewModel : BaseViewModel() {
    abstract fun insertFamilyMember(familyMember: FamilyMember)
    abstract fun getFamilyMemberById(id: Int)
    abstract fun deleteFamilyMember(id: Int)
    abstract var _familyMember: MutableLiveData<FamilyMember>
    abstract val familyMember: LiveData<FamilyMember>
    abstract var isEx: LiveData<Boolean>
}