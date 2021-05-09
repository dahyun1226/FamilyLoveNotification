package com.example.familylovenotification.model.data

import java.time.LocalDateTime

data class FamilyMember(
    val id: Int,
    var name: String?,
    var phoneNumber: String?,
    var sendCountToday: Int = 0,
    var sendCountLimitByDay: Int = 0,
    var sendHourInterval: Int = 5,
    var lastSendTime: LocalDateTime?,
    var isSendingMessage: Boolean = false
)