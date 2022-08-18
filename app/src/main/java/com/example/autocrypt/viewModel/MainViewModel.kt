package com.example.autocrypt.viewModel

import androidx.lifecycle.*
import androidx.paging.*
import com.example.autocrypt.CenterResult
import com.example.autocrypt.data.Key
import com.example.autocrypt.data.db.CenterData
import com.example.autocrypt.data.db.RoomRepository
import com.example.autocrypt.data.paging.CenterDataSource
import com.example.autocrypt.data.repository.CenterRepository
import com.example.autocrypt.data.response.CenterDataResponse
import com.example.autocrypt.domain.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val centerRepositoryImpl: CenterRepository,
    private val roomRepository: RoomRepository,
    private val repository: Repository
) : ViewModel() {

    private var centerList: MutableList<CenterDataResponse> = mutableListOf()

    val dbList: LiveData<List<CenterData>> = roomRepository.allData.asLiveData()


    /*   fun getAllRecords() : Flow<PagingData<CenterData>>{
           return Pager(config = PagingConfig(pageSize = 10, maxSize = 100),
           pagingSourceFactory = {roomRepository.getAllRecords()}).flow.cachedIn(viewModelScope)
       }*/


    private var currentLiveData : Flow<PagingData<CenterDataResponse>>? = null

      fun getData(): Flow<PagingData<CenterDataResponse>> {
          val ResultFlowData : Flow<PagingData<CenterDataResponse>> =
          repository.getCenterDataByPaging().cachedIn(viewModelScope)
          currentLiveData = ResultFlowData
          return ResultFlowData
      }


    suspend fun getCenterList(apiKey: String, page: Int) = runBlocking {
        val list = centerRepositoryImpl.getCenterList(apiKey, page)?.data
        list?.let { centerResult ->
            centerResult.forEach { centerResult ->
                centerList.add(
                    CenterDataResponse(
                        id = centerResult.id,
                        centerName = centerResult.centerName,
                        sido = centerResult.sido,
                        sigungu = centerResult.sigungu,
                        facilityName = centerResult.facilityName,
                        zipCode = centerResult.zipCode,
                        address = centerResult.address,
                        lat = centerResult.lat,
                        lng = centerResult.lng,
                        createdAt = centerResult.createdAt,
                        updatedAt = centerResult.updatedAt,
                        centerType = centerResult.centerType,
                        org = centerResult.org,
                        phoneNumber = centerResult.phoneNumber
                    )
                )
            }
            CenterResult.Success
        }
    }

/*suspend fun getRoomList() = viewModelScope.launch {
    val list = centerRepositoryImpl.getCenterList()?.data
    list?.let{ centerDBResult ->
        centerDBResult.forEach{centerDBResult ->
            roomRepository.insertRecord(
                    CenterData(
                    id = centerDBResult.id,
                    centerName = centerDBResult.centerName,
                    sido = centerDBResult.sido,
                    sigungu = centerDBResult.sigungu,
                    facilityName = centerDBResult.facilityName,
                    zipCode= centerDBResult.zipCode,
                    address = centerDBResult.address,
                    lat = centerDBResult.lat,
                    lng = centerDBResult.lng,
                    createdAt= centerDBResult.createdAt,
                    updatedAt = centerDBResult.updatedAt,
                    centerType = centerDBResult.centerType,
                    org = centerDBResult.org,
                    phoneNumber = centerDBResult.phoneNumber
                    )
            )
        }
        CenterResult.Success
    }
}*/
// fun getCenterDataPaging(): List<CenterDataResponse>{
//     return getData
// }


    fun getCenterData(): List<CenterDataResponse> {
        return centerList
    }
}