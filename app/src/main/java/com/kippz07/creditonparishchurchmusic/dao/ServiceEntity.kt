package com.kippz07.creditonparishchurchmusic.dao

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.Serializable
import java.time.LocalDate
import java.util.Date

@Entity(primaryKeys = ["date", "serviceType"])
data class ServiceEntity(
    @ColumnInfo val date: LocalDate,
    @ColumnInfo val serviceType: String
) : Serializable


@Entity
data class MusicEntity (
    @PrimaryKey(autoGenerate = true) val serviceId: Int,
    @ColumnInfo val date: LocalDate,
    @ColumnInfo val serviceType: String,
    @ColumnInfo val type: String,
    @ColumnInfo val title: String,
    @ColumnInfo val composer: String?,
    @ColumnInfo val link: String?
)

