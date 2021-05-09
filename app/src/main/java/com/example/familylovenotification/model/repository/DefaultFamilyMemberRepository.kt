package com.example.familylovenotification.model.repository

import com.example.familylovenotification.model.dao.FamilyMemberDao
import com.example.familylovenotification.model.data.FamilyMember
import com.example.familylovenotification.model.mapper.mapFamilyMember
import com.example.familylovenotification.model.mapper.mapFamilyMemberEntity
import com.example.familylovenotification.model.mapper.mapFamilyMemberList
import javax.inject.Inject

class DefaultFamilyMemberRepository @Inject constructor() :
    FamilyMemberRepository {

    @Inject
    lateinit var familyMemberDao: FamilyMemberDao

    override suspend fun insertFamilyMember(familyMember: FamilyMember){
        familyMemberDao.insertFamilyMember(familyMember.mapFamilyMemberEntity())
    }

    override suspend fun deleteFamilyMember(id: Int) =
        familyMemberDao.deleteFamilyMemberById(id)

    override suspend fun getAllFamilyMember(): List<FamilyMember> =
        familyMemberDao.getAllFamilyMember().mapFamilyMemberList()

    override suspend fun getFamilyMemberById(id: Int): FamilyMember {
        return familyMemberDao.getFamilyMemberById(id).mapFamilyMember()

    }
}