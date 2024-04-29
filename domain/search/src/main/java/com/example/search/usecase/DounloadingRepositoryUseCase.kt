package com.example.search.usecase

import com.example.search.models.Repository
import com.example.search.models.User
import com.example.search.repository.SearchRepository

class DounloadingRepositoryUseCase(private val searchRepository: SearchRepository) {
    suspend fun downloadRepository(
        user: User,
        repository: Repository,
    ) = searchRepository.downloadRepository(user, repository)
}
