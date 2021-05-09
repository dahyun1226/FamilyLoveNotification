package com.example.familylovenotification.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "FamilyMember")
data class FamilyMemberEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "phone_number")
    val phoneNumber: String?,
    @ColumnInfo(name = "send_count_today")
    val sendCountToday: Int = 0,
    @ColumnInfo(name = "send_count_limit_by_day")
    val sendCountLimitByDay: Int = 0,
    @ColumnInfo(name = "send_hour_interval")
    val sendHourInterval: Int = 5,
    @ColumnInfo(name = "last_send_time")
    val lastSendTime: LocalDateTime?,
    @ColumnInfo(name = "is_sending_message")
    val isSendingMessage: Boolean = false
)