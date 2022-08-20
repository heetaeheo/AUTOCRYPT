package com.example.autocrypt.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.autocrypt.data.entity.CenterDataEntity
import com.example.autocrypt.data.paging.CenterDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PagingRepository @Inject constructor(
    private val dataSource: CenterDataSource
) {

    fun getCenterDataByPaging(): Flow<PagingData<CenterDataEntity>>{
        return Pager(
            config = PagingConfig(pageSize = 100, enablePlaceholders = false),
            pagingSourceFactory = { dataSource }
        ).flow
    }

}