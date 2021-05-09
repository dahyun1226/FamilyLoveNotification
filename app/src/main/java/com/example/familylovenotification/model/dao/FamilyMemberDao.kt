package com.example.familylovenotification.model.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.familylovenotification.model.entity.FamilyMemberEntity

@Dao
interface FamilyMemberDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFamilyMember(familyMemberEntity: FamilyMemberEntity)

    @Query("DELETE FROM familymember WHERE id = :id")
    suspend fun deleteFamilyMemberById(id: Int)

    @Query("SELECT * FROM familymember")
    suspend fun getAllFamilyMember(): List<FamilyMemberEntity>

    @Query("SELECT * FROM familymember WHERE id = :id")
    suspend fun getFamilyMemberById(id: Int): FamilyMemberEntity
}