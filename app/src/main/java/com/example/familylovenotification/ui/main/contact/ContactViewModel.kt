package com.example.familylovenotification.ui.main.contact

import androidx.lifecycle.LiveData
import com.example.familylovenotification.base.BaseViewModel
import com.example.familylovenotification.model.data.FamilyMember

abstract class ContactViewModel : BaseViewModel() {

    abstract val familyMembers: LiveData<List<FamilyMember>>

    abstract fun getFamilyMembers()
}