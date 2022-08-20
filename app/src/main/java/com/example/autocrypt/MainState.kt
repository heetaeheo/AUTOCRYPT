package com.example.autocrypt

import com.example.autocrypt.data.entity.CenterDataEntity

sealed class MainState {

    object Loading : MainState()

    data class Success(val data: List<CenterDataEntity>) : MainState()
}