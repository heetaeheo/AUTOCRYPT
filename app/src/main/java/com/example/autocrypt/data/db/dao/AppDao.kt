package com.example.autocrypt.data.db.dao

import androidx.room.*
import com.example.autocrypt.data.entity.CenterDataEntity

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