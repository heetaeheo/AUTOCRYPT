package com.example.autocrypt.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM CenterData ORDER BY id DESC")
    fun getAllRecords(): Flow<List<CenterData>>

    @Insert
    fun insertRecord(centerData: CenterData)
}