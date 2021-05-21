package com.dahyun.familylovenotification.ui.contact

import com.dahyun.base.BaseViewModel
import com.dahyun.familylovenotification.data.FamilyMember
import java.time.LocalDateTime

class ContactViewModel : BaseViewModel() {
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