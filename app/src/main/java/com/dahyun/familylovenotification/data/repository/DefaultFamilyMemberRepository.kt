package com.dahyun.familylovenotification.data.repository

import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.data.dao.FamilyMemberDao
import javax.inject.Inject

class DefaultFamilyMemberRepository @Inject constructor(private val familyMemberDao: FamilyMemberDao) :
    FamilyMemberRepository {

    override suspend fun insertFamilyMember(familyMember: FamilyMember) =
        familyMemberDao.insertFamilyMember(familyMember)

    override suspend fun deleteFamilyMember(id: Int) =
        familyMemberDao.deleteFamilyMemberById(id)

    override suspend fun getAllFamilyMember(): List<FamilyMember> =
        familyMemberDao.getAllFamilyMember()

}