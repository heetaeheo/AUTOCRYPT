package com.example.autocrypt.data.response

import android.os.Parcelable
import com.example.autocrypt.data.db.CenterDataEntity
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CenterDataResponse(
    @SerializedName("id")
    val id: Long,
    @SerializedName("centerName")
    val centerName: String,
    @SerializedName("sido")
    val sido: String,
    @SerializedName("sigungu")
    val sigungu: String,
    @SerializedName("facilityName")
    val facilityName:String,
    @SerializedName("zipCode")
    val zipCode: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lng")
    val lng: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("updatedAt")
    val updatedAt: String,
    @SerializedName("centerType")
    val centerType: String,
    @SerializedName("org")
    val org: String,
    @SerializedName("phoneNumber")
    val phoneNumber: String
) : Parcelable {
    fun toCenterDataEntity() = CenterDataEntity(
        id = id,
        centerName = centerName,
        sido = sido,
        sigungu = sigungu,
        facilityName = facilityName,
        zipCode = zipCode,
        address = address,
        lat = lat,
        lng = lng,
        createdAt = createdAt,
        updatedAt = updatedAt,
        centerType = centerType,
        org = org,
        phoneNumber = phoneNumber,
    )
}