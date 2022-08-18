package com.example.autocrypt.data.db

import androidx.annotation.WorkerThread
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RoomRepository @Inject constructor(private val appDao: AppDao) {

     val allData = appDao.getAllRecords()


    @WorkerThread
    suspend fun insertRecord(centerData: CenterData){
        appDao.insertRecord(centerData)
    }




}