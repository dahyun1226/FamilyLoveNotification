package com.example.familylovenotification.base.util

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class RoomConverter {
    @TypeConverter
    fun longToLocalDateTime(value: Long?): LocalDateTime? {
        return value?.let {
            LocalDateTime.ofInstant(
                Instant.ofEpochMilli(it),
                TimeZone.getDefault().toZoneId()
            )
        }
    }

    @TypeConverter
    fun localDateTimeToLong(time: LocalDateTime?): Long? {
        return time?.atZone(ZoneId.systemDefault())?.toInstant()?.toEpochMilli()
    }
}