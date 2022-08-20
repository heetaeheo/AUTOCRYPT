package com.example.autocrypt.data.repository

import com.example.autocrypt.data.db.dao.AppDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val appDao: AppDao
) {

    suspend fun getRoomAllData() = withContext(Dispatchers.IO){
            appDao.getAllRecords()
    }


}

