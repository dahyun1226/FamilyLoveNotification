package com.dahyun.familylovenotification.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dahyun.familylovenotification.data.FamilyMember

@Dao
interface FamilyMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFamilyMember(familyMemberEntity: FamilyMember)

    @Query("DELETE FROM familymember WHERE id = :id")
    fun deleteFamilyMemberById(id: Int)

    @Query("SELECT * FROM familymember")
    fun getAllFamilyMember(): List<FamilyMember>

    @Query("SELECT * FROM familymember WHERE id = :id")
    fun getFamilyMemberById(id: Int): FamilyMember
}