package com.example.search.repository

import android.app.DownloadManager
import android.content.Context
import android.os.Environment
import androidx.core.net.toUri
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.database.db.Database
import com.example.search.api.SearchApi
import com.example.search.models.Repository
import com.example.search.models.User
import com.example.search.models.UserRepository
import com.example.search.models.paging.UserRepositoryPagingData
import com.example.search.models.toCacheRepositoryDB
import com.example.search.models.toCacheUserDB
import com.example.search.models.toRepositoryDB
import com.example.search.models.toUserDB
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SearchRepositoryImpl
    @Inject
    constructor(
        private val dataBase: Database,
        private val context: Context,
        private val searchApi: SearchApi,
    ) : SearchRepository {
        private val downloadManager = context.getSystemService(DownloadManager::class.java)

        override suspend fun getUserRepository(searchText: String, errorText: (String)->Unit): Flow<PagingData<UserRepository>> =
            Pager(
                config =
                    PagingConfig(
                        pageSize = 5,
                        enablePlaceholders = false,
                        initialLoadSize = 1,
                    ),
                pagingSourceFactory = {
                    UserRepositoryPagingData(
                        searchApi = searchApi,
                        searchText = searchText,
                        errorText = errorText
                    )
                },
            ).flow
                .flowOn(Dispatchers.IO)

        override suspend fun downloadRepository(
            user: User,
            repository: Repository,
        ): Long {
            val url = "${repository.htmlUrl}/archive/refs/heads/${repository.defaultBranch}.zip"

            val request =
                DownloadManager
                    .Request(url.toUri())
                    .setMimeType("application/zip")
                    .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE)
                    .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
                    .setTitle("${repository.name}.zip")
                    .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "${repository.name}.zip")
            saveLoadRepository(user, repository)
            return downloadManager.enqueue(request)
        }

    private fun saveLoadRepository(
        user: User,
        repository: Repository,
    ) {
        dataBase.cacheUserDao().saveUser(user.toCacheUserDB())
        dataBase.cacheRepositoryDao().saveRepository(repository.toCacheRepositoryDB(user.id))
    }
    }
