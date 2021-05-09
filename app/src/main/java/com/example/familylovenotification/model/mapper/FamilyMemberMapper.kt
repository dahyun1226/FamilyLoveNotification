package com.example.familylovenotification.model.mapper

import com.example.familylovenotification.model.data.FamilyMember
import com.example.familylovenotification.model.entity.FamilyMemberEntity

fun FamilyMember.mapFamilyMemberEntity(): FamilyMemberEntity =
    FamilyMemberEntity(
        id,
        name,
        phoneNumber,
        sendCountLimitByDay,
        sendCountToday,
        sendHourInterval,
        lastSendTime,
        isSendingMessage
    )

fun FamilyMemberEntity.mapFamilyMember(): FamilyMember {
    return FamilyMember(
        id,
        name,
        phoneNumber,
        sendCountLimitByDay,
        sendCountToday,
        sendHourInterval,
        lastSendTime,
        isSendingMessage
    )
}

fun List<FamilyMemberEntity>.mapFamilyMemberList(): List<FamilyMember> =
    map { it.mapFamilyMember() }