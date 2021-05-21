package com.dahyun.familylovenotification.data.converter

import androidx.room.TypeConverter
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

class TimeConverter {
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