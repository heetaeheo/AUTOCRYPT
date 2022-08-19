package com.example.autocrypt.domain.repository

import com.example.autocrypt.data.db.AppDao
import com.example.autocrypt.data.db.CenterDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RoomRepository @Inject constructor(
    private val appDao: AppDao
) {

    private var data : List<CenterDataEntity> = mutableListOf()

    suspend fun getRoomAllData() = withContext(Dispatchers.IO){
        appDao.getAllRecords()
    }


}

