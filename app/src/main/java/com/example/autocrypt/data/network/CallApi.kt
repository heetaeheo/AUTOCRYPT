package com.example.autocrypt.data.network

import com.example.autocrypt.data.Key
import com.example.autocrypt.data.response.CenterInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CallApi {

 @GET("15077586/v1/centers")
    suspend fun getData(
        @Query("serviceKey") serviceKey: String = Key.Decoding,
        @Query("page") page: Int?
    ): Response<CenterInfo>



}