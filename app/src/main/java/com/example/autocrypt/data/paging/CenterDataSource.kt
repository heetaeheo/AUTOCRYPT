package com.example.autocrypt.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.network.CallApi
import com.example.autocrypt.data.repository.CenterRepository
import com.example.autocrypt.data.response.CenterDataResponse
import com.example.autocrypt.data.response.CenterInfo
import com.example.autocrypt.di.annotation.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_START_INDEX =1

class CenterDataSource(
    private val callApi: CallApi,
): PagingSource<Int,CenterDataResponse>(){

    override fun getRefreshKey(state: PagingState<Int, CenterDataResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CenterDataResponse> {
        val page = params.key ?:PAGE_START_INDEX
        val apiKey = Key.Decoding
        return try {
            val response = callApi.getData(apiKey,page)
            LoadResult.Page(
                data = response.body()!!.data,
                prevKey = if(page == PAGE_START_INDEX) null else page.minus(1),
                nextKey = if(!response.isSuccessful) null else page.plus(1)
            )
        } catch (exception: IOException){
            return LoadResult.Error(exception)
        } catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}