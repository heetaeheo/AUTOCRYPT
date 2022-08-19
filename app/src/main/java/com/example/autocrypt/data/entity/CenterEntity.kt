package com.example.autocrypt.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Entity(tableName = "centerEntity")
data class CenterEntity(
    @PrimaryKey
    val currentCount: String,
    @Json(name = "data")
    val data: List<Center>
    )

data class Center(
    @Json(name = "id")
    val id: String,
    @Json(name = "centerName")
    val centerName: String,
    @Json(name = "sido")
    val sido: String,
    @Json(name = "sigungu")
    val sigungu: String,
    @Json(name = "facilityName")
    val facilityName: String,
    @Json(name = "zipCode")
    val zipCode: String,
    @Json(name = "address")
    val address: String,
    @Json(name = "lat")
    val lat: String,
    @Json(name = "lng")
    val lng: String,
    @Json(name = "createdAt")
    val createdAt: String,
    @Json(name = "updatedAt")
    val updatedAt: String,
    @Json(name = "centerType")
    val centerType: String,
    @Json(name = "org")
    val org: String,
    @Json(name = "phoneNumber")
    val phoneNumber: String
)

class Converters{
    @TypeConverter
    fun listToJson(value: List<Center>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value:String) = Gson().fromJson(value,
    Array<Center>::class.java).toList()

}