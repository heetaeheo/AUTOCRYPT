package com.example.autocrypt.domain.repository

import com.example.autocrypt.data.db.AppDao
import com.example.autocrypt.data.entity.CenterDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val appDao: AppDao
) {

    suspend fun getRoomAllData() = withContext(Dispatchers.IO){
        runBlocking {
            delay(5000)
            appDao.getAllRecords()
        }
    }


}

