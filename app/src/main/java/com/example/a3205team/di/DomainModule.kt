package com.example.a3205team.di

import com.example.history.repository.HistoryRepository
import com.example.history.usecase.GetSavedRepositoryUseCase
import com.example.search.repository.SearchRepository
import com.example.search.usecase.DounloadingRepositoryUseCase
import com.example.search.usecase.GetUsersRepositoryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {
    @Provides
    fun providesGetUsersRepositoryUseCase(repository: SearchRepository): GetUsersRepositoryUseCase = GetUsersRepositoryUseCase(repository)

    @Provides
    fun providesDounloadingRepositoryUseCase(repository: SearchRepository): DounloadingRepositoryUseCase =
        DounloadingRepositoryUseCase(repository)

    @Provides
    fun providesGetSavedRepositoryUseCase(repository: HistoryRepository): GetSavedRepositoryUseCase = GetSavedRepositoryUseCase(repository)
}
