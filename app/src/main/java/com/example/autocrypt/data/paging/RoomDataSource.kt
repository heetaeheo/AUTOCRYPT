/*package com.example.autocrypt.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.AppDao
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.network.CallApiPaging
import com.example.autocrypt.data.response.CenterDataResponse
import retrofit2.HttpException
import java.io.IOException

private const val PAGE_START_INDEX =1

class RoomDataSource(
    private val dao: AppDao,
): PagingSource<Int,CenterData>(){

    override fun getRefreshKey(state: PagingState<Int, CenterData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, CenterData> {
        val page = params.key ?:PAGE_START_INDEX
        val apiKey = Key.Decoding
        return try {
            val response = dao.getAllRecords()
            PagingSource.LoadResult.Page(
                data = response.body(),
                prevKey = if(page == PAGE_START_INDEX) null else -1,
                nextKey = if(!response.isSuccessful) null else page.plus(1)
            )
        } catch (exception: IOException){
            return PagingSource.LoadResult.Error(exception)
        } catch (exception: HttpException){
            return PagingSource.LoadResult.Error(exception)
        }
    }
}*/