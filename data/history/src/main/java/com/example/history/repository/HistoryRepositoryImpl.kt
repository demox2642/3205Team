package com.example.history.repository

import com.example.database.db.Database
import com.example.history.models.HistoryUserRepo
import com.example.history.models.toHistoryUserRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class HistoryRepositoryImpl
    @Inject
    constructor(
        private val dataBase: Database,
    ) : HistoryRepository {
        override suspend fun getHistory(): Flow<List<HistoryUserRepo>> =
            flow {
                val dataList = dataBase.cacheUserDao().getUsersRepository()
                val result = mutableListOf<HistoryUserRepo>()
                dataList.forEach {
                    result += it.toHistoryUserRepo()
                }
                emit(result)
            }.flowOn(Dispatchers.IO)
    }
