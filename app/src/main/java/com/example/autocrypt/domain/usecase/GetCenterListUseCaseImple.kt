/*package com.example.autocrypt.domain.usecase

import com.example.autocrypt.CenterResult
import com.example.autocrypt.data.repository.CenterRepository
import com.example.autocrypt.data.response.CenterDataResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCenterListUseCaseImple @Inject constructor(
    private val centerRepositoryImpl: CenterRepository,
    private val ioDispatcher : CoroutineDispatcher
)  {
    private var centerList : MutableList<CenterDataResponse> = mutableListOf()

    suspend fun getCenterList() = withContext(ioDispatcher){
        val list = centerRepositoryImpl.getCenterList()?.data
        list?.let{ centerResult ->
            centerResult.forEach{centerResult ->
                centerList.add(
                    CenterDataResponse(
                        id = centerResult.id,
                        centerName = centerResult.centerName,
                        sido = centerResult.sido,
                        sigungu = centerResult.sigungu,
                        facilityName = centerResult.facilityName,
                        zipCode= centerResult.zipCode,
                        address = centerResult.address,
                        lat = centerResult.lat,
                        lng = centerResult.lng,
                        createdAt= centerResult.createdAt,
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

    suspend fun getCenterData(): List<CenterDataResponse>{
        return centerList
    }

}*/