package com.example.autocrypt.data.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.AppDao
import com.example.autocrypt.data.db.CenterDataEntity
import com.example.autocrypt.data.network.CallApi
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

private const val PAGE_START_INDEX =1

class CenterDataSource @Inject constructor(
    private val callApi: CallApi,
    private val appDao: AppDao
): PagingSource<Int, CenterDataEntity>(){

    override fun getRefreshKey(state: PagingState<Int, CenterDataEntity>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.nextKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.prevKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CenterDataEntity> {
        val page = params.key ?:PAGE_START_INDEX
        val apiKey = Key.Decoding
        return try {
            val response = callApi.getData(apiKey,page)

            if (response.isSuccessful) {
                Log.d("TAG", "retrofit: ${response.body()!!.data}")
                appDao.insertRecords(response.body()!!.data.map { it.toCenterDataEntity() })
            }

            val start = (page.toLong() - 1) * 10 + 1

            LoadResult.Page(
                data = appDao.getAllRecords(start, start + 9),
                prevKey = if(page == 1) null else page.minus(1),
                nextKey = if(page == 10) null else page.plus(1)
            )
        } catch (exception: IOException){
            return LoadResult.Error(exception)
        } catch (exception: HttpException){
            return LoadResult.Error(exception)
        }
    }
}