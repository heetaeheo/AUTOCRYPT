package com.example.autocrypt.data.repository

import android.database.Observable
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.network.CallApi
import com.example.autocrypt.data.response.CenterInfo
import com.example.autocrypt.di.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject



class CenterRepositoryImpl @Inject constructor(
    private val api: CallApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CenterRepository {

    /*override suspend fun getCenterList() =
        withContext(ioDispatcher) {
            val response = api.getData()
            if (response.isSuccessful) {
                response.body()!!
            } else {
                null
            }
        }
*/
    override suspend fun getCenterList(apiKey: String, page: Int): CenterInfo? =
        withContext(ioDispatcher) {
            val response = api.getData(apiKey,page)
            if (response.isSuccessful){
                response.body()!!
            }else{
                null
            }

    }
}