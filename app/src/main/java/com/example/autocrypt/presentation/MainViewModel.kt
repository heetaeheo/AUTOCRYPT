package com.example.autocrypt.presentation

import androidx.lifecycle.*
import androidx.paging.*
import com.example.autocrypt.data.entity.CenterDataEntity
import com.example.autocrypt.data.repository.PagingRepository
import com.example.autocrypt.data.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val pagingRepository: PagingRepository,
    private val roomRepository: RoomRepository
) : ViewModel() {

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
      fun getRoomData() = viewModelScope.async {
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




