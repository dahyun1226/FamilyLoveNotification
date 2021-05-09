package com.example.familylovenotification.model.repository

import com.example.familylovenotification.model.data.FamilyMember

interface FamilyMemberRepository {
    suspend fun insertFamilyMember(familyMember: FamilyMember)
    suspend fun deleteFamilyMember(id: Int)
    suspend fun getAllFamilyMember(): List<FamilyMember>
    suspend fun getFamilyMemberById(id: Int): FamilyMember
}