package com.example.autocrypt.viewModel

import android.util.Log
import androidx.lifecycle.*
import androidx.paging.*
import com.example.autocrypt.MainState
import com.example.autocrypt.data.db.CenterDataEntity
import com.example.autocrypt.domain.repository.PagingRepository
import com.example.autocrypt.domain.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pagingRepository: PagingRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

    private var centerList: MutableList<CenterDataEntity> = mutableListOf()

    private val _centers = MutableLiveData<MainState>(MainState.Loading)
    val centers: LiveData<MainState> get() = _centers


    private var currentData: Flow<PagingData<CenterDataEntity>>? = null

    fun getData(): Flow<PagingData<CenterDataEntity>> {
        val ResultFlowData: Flow<PagingData<CenterDataEntity>> =
            pagingRepository.getCenterDataByPaging().cachedIn(viewModelScope)
        currentData = ResultFlowData
        return ResultFlowData
    }

    @Suppress("CAST_NEVER_SUCCEEDS")
      fun getRoomData() = viewModelScope.launch{

          _centers.value = MainState.Success(roomRepository.getRoomAllData())
     }

    fun getRoomList() : List<CenterDataEntity>? {
        when(centers.value) {
            is MainState.Success -> {
                return (centers.value as MainState.Success).data
            }
            else -> {}
        }
        return null
    }
}




