package com.example.autocrypt.data.paging

import androidx.paging.PageKeyedDataSource
import androidx.paging.PagingSource
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.repository.CenterRepository
import com.example.autocrypt.data.response.CenterInfo
import com.example.autocrypt.di.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CenterDataSource(
    private val centerRepository: CenterRepository,
    private val coroutineScope: CoroutineScope
): PageKeyedDataSource<Int,CenterInfo>(){

    override fun loadAfter(
        params: LoadParams<Int>,
        callback: LoadCallback<Int, CenterInfo>
    ) {
        coroutineScope.launch {
            val centerPagingList = centerRepository.getCenterList(Key.Decoding, 1)
            callback.onResult(,)
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, CenterInfo>) {
        TODO("Not yet implemented")
    }

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, CenterInfo>
    ) {
        TODO("Not yet implemented")
    }
}