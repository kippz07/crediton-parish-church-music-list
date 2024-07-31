package com.kippz07.creditonparishchurchmusic.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.time.LocalDate

@Dao
interface ServiceDao {
    @Query("SELECT * FROM ServiceEntity")
    suspend fun getAll(): List<ServiceEntity>

//    @Query("SELECT * FROM ServiceEntity")
//    suspend fun get(): List<ServiceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serviceEntity: ServiceEntity)

    @Query("DELETE FROM ServiceEntity")
    suspend fun deleteAll()
}

@Dao
interface MusicDao {
    @Query("SELECT * FROM MusicEntity WHERE date = :date AND serviceType = :serviceType")
    suspend fun get(date: LocalDate, serviceType: String): List<MusicEntity>

    @Query("SELECT * FROM MusicEntity")
    suspend fun getAll(): List<MusicEntity>

//    @Query("SELECT * FROM ServiceEntity")
//    suspend fun get(): List<ServiceEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(musicEntity: MusicEntity)

    @Query("DELETE FROM MusicEntity")
    suspend fun deleteAll()
}