package com.dahyun.familylovenotification.data

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "FamilyMember")
data class FamilyMember(
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
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readSerializable() as LocalDateTime,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeString(phoneNumber)
        parcel.writeInt(sendCountToday)
        parcel.writeInt(sendCountLimitByDay)
        parcel.writeInt(sendHourInterval)
        parcel.writeSerializable(lastSendTime)
        parcel.writeByte(if (isSendingMessage) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<FamilyMember> {
        override fun createFromParcel(parcel: Parcel): FamilyMember {
            return FamilyMember(parcel)
        }

        override fun newArray(size: Int): Array<FamilyMember?> {
            return arrayOfNulls(size)
        }
    }
}
