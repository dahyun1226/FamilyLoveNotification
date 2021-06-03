package com.dahyun.familylovenotification.data.repository

import com.dahyun.familylovenotification.data.FamilyMember

interface FamilyMemberRepository {
    suspend fun insertFamilyMember(familyMember: FamilyMember)
    suspend fun deleteFamilyMember(id: Int)
    suspend fun getAllFamilyMember(): List<FamilyMember>
}