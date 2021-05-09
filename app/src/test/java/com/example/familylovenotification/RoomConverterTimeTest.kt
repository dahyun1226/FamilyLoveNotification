package com.example.familylovenotification

import androidx.room.TypeConverter
import org.junit.Test
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class RoomConverterTimeTest {

    @Test
    fun addition_isCorrect() {
        val x = LocalDateTime.now()
        print(x)
        val y = RoomConverter.longToLocalDateTime(RoomConverter.localDateTimeToLong(x))
        print(y)
    }
}

class RoomConverter {
    companion object {
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
}

