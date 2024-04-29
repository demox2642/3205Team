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
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchScreenVM
    @Inject
    constructor(
        private val getUsersRepositoryUseCase: GetUsersRepositoryUseCase,
        private val dounloadingRepositoryUseCase: DounloadingRepositoryUseCase
    ) : ViewModel() {
        private val _userRepositoryList = MutableStateFlow<PagingData<UserRepository>>(PagingData.empty())
        val userRepositoryList = _userRepositoryList.asStateFlow()
    
        private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()



    fun changeSearchText(text: String){
        _searchText.value = text
    }

    fun search(){
        viewModelScope.launch(Dispatchers.IO) {
            getUsersRepositoryUseCase.getUserRepository(searchText = _searchText.value)
                .cachedIn(viewModelScope)
                .collectLatest {
                    _userRepositoryList.value = it
                }
        }
    }

    fun dounloadingRepository(user: User, repository: Repository){
        viewModelScope.launch(Dispatchers.IO) {
            dounloadingRepositoryUseCase.downloadRepository(user, repository)
        }
    }
    }
