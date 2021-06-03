package com.dahyun.familylovenotification.test

import com.dahyun.familylovenotification.data.FamilyMember
import java.time.LocalDateTime

object MockFamilyMember {
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
                true
            )
        )
    }
}