package com.example.autocrypt.data.response

import com.example.autocrypt.data.Key


data class CenterInfo(
    // val parameters:Parameters = Parameters(),
    val data: List<CenterDataResponse>
){
    data class Parameters(
        val page: Int? = 1,
        val perPage:Int? = 10,
        val returnType:String? = null
    )
}
