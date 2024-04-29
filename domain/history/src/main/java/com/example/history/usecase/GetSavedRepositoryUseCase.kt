package com.example.history.usecase

import com.example.history.repository.HistoryRepository

class GetSavedRepositoryUseCase(private val historyRepository: HistoryRepository) {
    suspend fun getHistory() = historyRepository.getHistory()
}
