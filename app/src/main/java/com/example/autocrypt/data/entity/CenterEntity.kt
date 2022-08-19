package com.example.autocrypt.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

@Entity(tableName = "centerEntity")
data class CenterEntity(
    @PrimaryKey
    val currentCount: String,
    val data: List<Center>
    )

data class Center(
    val id: String,
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

class Converters{
    @TypeConverter
    fun listToJson(value: List<Center>) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value:String) = Gson().fromJson(value,
    Array<Center>::class.java).toList()

}