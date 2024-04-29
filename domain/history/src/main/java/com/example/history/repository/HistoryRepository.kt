package com.example.history.repository

import com.example.history.models.HistoryUserRepo
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun getHistory(): Flow<List<HistoryUserRepo>>
}
