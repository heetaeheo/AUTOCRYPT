package com.example.autocrypt.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.network.CallApi
import com.example.autocrypt.data.paging.CenterDataSource
import com.example.autocrypt.data.response.CenterDataResponse
import com.example.autocrypt.domain.model.CenterListModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Repository @Inject constructor(
    private val callApi: CallApi
) {

    fun getCenterDataByPaging(): Flow<PagingData<CenterDataResponse>>{
        return Pager(
            config = PagingConfig(pageSize = 90, enablePlaceholders = false),
            pagingSourceFactory = {CenterDataSource(callApi)}
        ).flow
    }


}