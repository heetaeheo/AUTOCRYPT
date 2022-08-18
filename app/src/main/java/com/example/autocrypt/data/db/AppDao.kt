package com.example.autocrypt.data.db

import androidx.paging.PagingSource
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AppDao {

    @Query("SELECT * FROM CenterData ORDER BY id")
    fun getAllRecords(): PagingSource<Int,CenterData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecord(centerData: CenterData)

    @Query("DELETE FROM CenterData")
    fun deleteRecordAll()
}