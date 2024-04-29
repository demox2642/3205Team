package com.example.history.screens.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.history.models.HistoryUserRepo
import com.example.history.usecase.GetSavedRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryScreenVM
    @Inject
    constructor(
        private val getSavedRepository: GetSavedRepositoryUseCase,
    ) : ViewModel() {
        private val _historyList = MutableStateFlow<List<HistoryUserRepo>>(emptyList())
        val historyList = _historyList.asStateFlow()

        init {

            viewModelScope.launch(Dispatchers.IO) {
                getSavedRepository.getHistory().collectLatest {
                    Log.e("History", "list = $it")
                    _historyList.value = it
                }
            }
        }
    }
