package com.example.autocrypt.data.repository

import com.example.autocrypt.data.response.CenterInfo

interface CenterRepository {
    suspend fun getCenterList(apiKey:String,page:Int
    ): CenterInfo?
}