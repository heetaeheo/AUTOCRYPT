package com.example.autocrypt.domain.repository

import com.example.autocrypt.domain.model.CenterListModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun getData(): Flow<List<CenterListModel>>
}