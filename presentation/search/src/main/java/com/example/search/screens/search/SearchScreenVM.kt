package com.example.search.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.search.models.Repository
import com.example.search.models.User
import com.example.search.models.UserRepository
import com.example.search.usecase.DounloadingRepositoryUseCase
import com.example.search.usecase.GetUsersRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenVM
    @Inject
    constructor(
        private val getUsersRepositoryUseCase: GetUsersRepositoryUseCase,
        private val dounloadingRepositoryUseCase: DounloadingRepositoryUseCase,
    ) : ViewModel() {
        private val _userRepositoryList = MutableStateFlow<PagingData<UserRepository>>(PagingData.empty())
        val userRepositoryList = _userRepositoryList.asStateFlow()

        private val _searchText = MutableStateFlow("")
        val searchText = _searchText.asStateFlow()

        private val _loadState = MutableStateFlow(false)
        val loadState = _loadState.asStateFlow()

        private val _errorText = MutableStateFlow("")
        val errorText = _errorText.asStateFlow()

        fun changeSearchText(text: String) {
            _searchText.value = text
            _loadState.value = true
            viewModelScope.launch(Dispatchers.IO) {
                autoSearch()
            }
        }

    fun cleanError(){
        _errorText.value = ""
    }

        suspend fun autoSearch() {
            _searchText
                .debounce(500)
                .distinctUntilChanged()
                .collectLatest { it ->
                    viewModelScope.launch(Dispatchers.IO) {
                        getUsersRepositoryUseCase
                            .getUserRepository(searchText = it,errorText={ _errorText.value = it})
                            .cachedIn(viewModelScope)
                            .collectLatest {
                                _userRepositoryList.value = it
                                _loadState.value = false
                            }
                    }
                }

        }

        fun search() {
            _loadState.value = true
            viewModelScope.launch {
                getUsersRepositoryUseCase
                    .getUserRepository(searchText = _searchText.value,errorText={ _errorText.value = it})
                    .cachedIn(viewModelScope)
                    .collectLatest {
                        _userRepositoryList.value = it
                        _loadState.value = false
                    }
            }
        }

        fun dounloadingRepository(
            user: User,
            repository: Repository,
        ) {
            viewModelScope.launch(Dispatchers.IO) {
                dounloadingRepositoryUseCase.downloadRepository(user, repository)
            }
        }
    }
