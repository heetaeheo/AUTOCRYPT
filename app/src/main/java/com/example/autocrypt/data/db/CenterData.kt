package com.example.autocrypt.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CenterData")
class CenterData (
    @PrimaryKey(autoGenerate = false)@ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "centerName") val centerName : String,
    @ColumnInfo(name = "sido") val sido: String,
    @ColumnInfo(name = "sigungu")val sigungu:String,
    @ColumnInfo(name = "facilityName")val facilityName:String,
    @ColumnInfo(name = "zipCode")val zipCode:String,
    @ColumnInfo(name = "address")val address:String,
    @ColumnInfo(name = "lat") val lat:String,
    @ColumnInfo(name = "lng") val lng:String,
    @ColumnInfo(name = "createdAt")val createdAt:String,
    @ColumnInfo(name = "updatedAt")val updatedAt:String,
    @ColumnInfo(name = "centerType")val centerType:String,
    @ColumnInfo(name = "org")val org:String,
    @ColumnInfo(name = "phoneNumber")val phoneNumber:String
)
