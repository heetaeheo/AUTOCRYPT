package com.example.autocrypt.viewModel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.autocrypt.data.db.CenterDataEntity
import com.example.autocrypt.data.response.CenterDataResponse
import com.example.autocrypt.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {


    private var currentData: Flow<PagingData<CenterDataEntity>>? = null

     suspend fun getData(): Flow<PagingData<CenterDataEntity>> {
        val ResultFlowData: Flow<PagingData<CenterDataEntity>> =
            repository.getCenterDataByPaging().cachedIn(viewModelScope)
        currentData = ResultFlowData
        return ResultFlowData
    }



}

