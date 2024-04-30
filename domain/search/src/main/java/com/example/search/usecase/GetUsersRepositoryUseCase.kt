package com.example.search.usecase

import androidx.paging.PagingData
import com.example.search.models.UserRepository
import com.example.search.repository.SearchRepository
import kotlinx.coroutines.flow.Flow

class GetUsersRepositoryUseCase(private val repository: SearchRepository) {
    suspend fun getUserRepository(searchText: String): Flow<PagingData<UserRepository>> {
        return repository.getUserRepository(searchText)
    }
}
