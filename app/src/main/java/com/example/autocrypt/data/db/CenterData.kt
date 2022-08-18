package com.example.autocrypt.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "CenterData")
class CenterData (
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("id")@ColumnInfo(name = "id")val id: String,
    @field:SerializedName("centerName")@ColumnInfo(name = "centerName") val centerName : String,
    @field:SerializedName("sido") @ColumnInfo(name = "sido") val sido: String,
    @field:SerializedName("sigungu") @ColumnInfo(name = "sigungu")val sigungu:String,
    @field:SerializedName("facilityName") @ColumnInfo(name = "facilityName")val facilityName:String,
    @field:SerializedName("zipCode") @ColumnInfo(name = "zipCode")val zipCode:String,
    @field:SerializedName("address") @ColumnInfo(name = "address")val address:String,
    @field:SerializedName("lat") @ColumnInfo(name = "lat") val lat:String,
    @field:SerializedName("lng") @ColumnInfo(name = "lng") val lng:String,
    @field:SerializedName("createdAt")@ColumnInfo(name = "createdAt")val createdAt:String,
    @field:SerializedName("updatedAt")@ColumnInfo(name = "updatedAt")val updatedAt:String,
    @field:SerializedName("centerType")@ColumnInfo(name = "centerType")val centerType:String,
    @field:SerializedName("org") @ColumnInfo(name = "org")val org:String,
    @field:SerializedName("phoneNumber") @ColumnInfo(name = "phoneNumber")val phoneNumber:String
)
