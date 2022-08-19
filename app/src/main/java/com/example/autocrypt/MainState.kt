package com.example.autocrypt

import androidx.annotation.StringRes
import com.example.autocrypt.data.db.CenterDataEntity

sealed class MainState {

    object Loading : MainState()

    data class Success(val data: List<CenterDataEntity>) : MainState()
}