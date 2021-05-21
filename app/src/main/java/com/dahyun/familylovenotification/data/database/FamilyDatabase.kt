package com.dahyun.familylovenotification.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.dahyun.familylovenotification.data.FamilyMember
import com.dahyun.familylovenotification.data.converter.TimeConverter
import com.dahyun.familylovenotification.data.dao.FamilyMemberDao
import com.dahyun.familylovenotification.data.database.FamilyMemberDatabase.Companion.VERSION

@Database(
    entities = [
        FamilyMember::class
    ],
    version = VERSION,
    exportSchema = false
)

@TypeConverters(TimeConverter::class)
abstract class FamilyMemberDatabase : RoomDatabase() {

    abstract fun familyMemberDao(): FamilyMemberDao

    companion object {
        const val VERSION = 1
        private const val TABLE_NAME = "family_member_db"

        @Volatile
        private var instance: FamilyMemberDatabase? = null

        fun getInstance(context: Context): FamilyMemberDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): FamilyMemberDatabase {
            return Room.databaseBuilder(context, FamilyMemberDatabase::class.java, TABLE_NAME)
                .build()
        }
    }
}