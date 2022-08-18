package com.example.autocrypt.data.network

import android.database.Observable
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.response.CenterInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CallApiPaging {

 @GET("15077586/v1/centers")
    suspend fun getData(
        @Query("serviceKey") serviceKey: String = Key.Decoding,
        @Query("page") page: Int?
    ): Response<CenterData>



}