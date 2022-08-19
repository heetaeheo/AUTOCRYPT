package com.example.autocrypt.data.db

import androidx.paging.PagingData
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.example.autocrypt.data.entity.Center
import com.example.autocrypt.data.response.CenterDataResponse
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json
import java.util.concurrent.Flow

@Entity(tableName = "CenterData")
data class CenterDataEntity (
    @PrimaryKey(autoGenerate = false)
     val id: Long,
    val centerName: String,
    val sido: String,
    val sigungu: String,
    val facilityName: String,
    val zipCode: String,
    val address: String,
    val lat: String,
    val lng: String,
    val createdAt: String,
    val updatedAt: String,
    val centerType: String,
    val org: String,
    val phoneNumber: String
)
