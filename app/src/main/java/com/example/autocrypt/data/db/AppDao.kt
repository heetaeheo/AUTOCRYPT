package com.example.autocrypt.data.db

import androidx.room.*

@Dao
interface AppDao {

    @Query("SELECT * FROM CenterData WHERE id BETWEEN :start AND :end")
    suspend fun getRangeRecords(start: Long, end: Long): List<CenterDataEntity>

    @Query("SELECT * FROM CenterData")
    suspend fun getAllRecords(): List<CenterDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecords(centerDatumEntities: List<CenterDataEntity>)

    @Query("DELETE FROM CenterData")
    suspend fun deleteRecordAll()
}