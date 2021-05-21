package com.dahyun.familylovenotification.ui.contact

import com.dahyun.base.BaseViewModel
import com.dahyun.familylovenotification.data.FamilyMember
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(): BaseViewModel() {
    override var logTag: String = "ContactViewModel"

    var familyMembers = mockFamilyMember()
}


fun mockFamilyMember(): List<FamilyMember> {
    return listOf(
        FamilyMember(
            1,
            "다현",
            "01023021226",
            0,
            0,
            3,
            LocalDateTime.of(2021,3,31,3,3),
            false
        )
    )
}