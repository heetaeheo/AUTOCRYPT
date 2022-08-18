package com.example.autocrypt.data.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CenterEntity(
    val id: Integer,
    val centerName: String,
    val sido: String,
    val sigungu: String,
    val facilityName:String,
    val zipCode: String,
    val address: String,
    val lat: String,
    val lng: String,
    val createdAt: String,
    val updatedAt: String,
    val centerType: String,
    val org: String,
    val phoneNumber: String
):Parcelable