package com.kippz07.creditonparishchurchmusic.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.time.LocalDate

@Database(entities = [ServiceEntity::class, MusicEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class ServiceDatabase : RoomDatabase() {
    abstract fun ServiceDao(): ServiceDao
    abstract fun MusicDao(): MusicDao
}

object DatabaseBuilder {
    private var INSTANCE: ServiceDatabase? = null
    fun getInstance(context: Context): ServiceDatabase {
        if (INSTANCE == null) {
            synchronized(ServiceDatabase::class) {
                INSTANCE = buildRoomDB(context)
            }
        }
        return INSTANCE!!
    }
    private fun buildRoomDB(context: Context) =
        Room.databaseBuilder(
            context.applicationContext,
            ServiceDatabase::class.java,
            "cpc-music-list"
        ).build()
}

class Converters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: LocalDate?): Long? {
        return date?.toEpochDay()
    }
}