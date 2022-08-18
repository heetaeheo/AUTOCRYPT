package com.example.autocrypt.domain.model

data class CenterListModel(val id: Integer,
                           val centerName: String,
                           val facilityName:String,
                           val address: String,
                           val lat: String,
                           val lng: String,
                           val updatedAt: String,
                           val centerType: String,
                           val phoneNumber: String
)