package com.example.autocrypt

sealed class CenterResult{

    object Success : CenterResult()
    object Loading : CenterResult()
    object Failure : CenterResult()

}
