package com.example.autocrypt.data.entity


import androidx.room.Entity
import androidx.room.PrimaryKey


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
