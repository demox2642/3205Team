package com.example.search.repository

import androidx.paging.PagingData
import com.example.search.models.Repository
import com.example.search.models.User
import com.example.search.models.UserRepository
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getUserRepository(searchText: String, errorText: (String)->Unit): Flow<PagingData<UserRepository>>

    suspend fun downloadRepository(
        user: User,
        repository: Repository,
    ): Long
}
