package com.example.autocrypt.data.db

import androidx.room.*

@Dao
interface AppDao {

    @Query("SELECT * FROM CenterData WHERE id BETWEEN :start AND :end")
    suspend fun getAllRecords(start: Long, end: Long): List<CenterDataEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecords(centerDatumEntities: List<CenterDataEntity>)

    @Query("DELETE FROM CenterData")
    suspend fun deleteRecordAll()
}