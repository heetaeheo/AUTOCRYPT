/*package com.example.autocrypt.data.repository

import android.database.Observable
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.network.CallApiPaging
import com.example.autocrypt.data.response.CenterInfo
import kotlinx.coroutines.withContext


interface CenterPagingRepository{
   suspend fun getDataList(
        apikey : String,
        page : Int,
    ): Observable<CenterInfo>
}

class CenterPagingRepositoryImple(private val callApiPaging: CallApiPaging):CenterPagingRepository{
    override suspend fun getDataList(
        apikey: String,
        page: Int,
    ): Observable<CenterInfo>{
        return callApiPaging.getData(apikey,page)
    }

interface CenterDataSource{
        suspend fun getCenterPagingList() : Observable<CenterInfo>
    }

class CenterDataSourceImple(private val centerPagingRepository: CenterPagingRepository) : CenterDataSource{

    override suspend fun getCenterPagingList(): Observable<CenterInfo> {
         return centerPagingRepository.getDataList(Key.Decoding,1)
        }

    }
}*/