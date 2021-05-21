package com.dahyun.familylovenotification.ui.contact

import com.dahyun.base.BaseViewModel
import com.dahyun.familylovenotification.test.MockFamilyMember
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(): BaseViewModel() {
    override var logTag: String = "ContactViewModel"

    var familyMembers = MockFamilyMember.mockFamilyMember()
}
