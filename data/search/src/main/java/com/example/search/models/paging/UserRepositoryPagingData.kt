package com.example.search.models.paging

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.search.api.SearchApi
import com.example.search.models.UserRepository
import com.example.search.models.server_response.toUserRepository

class UserRepositoryPagingData(
    private val searchApi: SearchApi,
    private val searchText: String,
) : PagingSource<Int, UserRepository>() {
    override fun getRefreshKey(state: PagingState<Int, UserRepository>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserRepository> {
        val page: Int = params.key ?: 0
        val loadSize = params.loadSize
        val maxPage = 1000 / loadSize

        val userRepositoryList = mutableListOf<UserRepository>()

        try {
            val usersList =
                searchApi.getUsers(
                    searchText = searchText,
                    pageSize = loadSize,
                    pageNum = page,
                ).items
            println("usersList = $usersList")
            usersList.forEach { user ->
                val repositoryList = searchApi.getRepository(user.login)
                if (repositoryList.filter { it.size > 1 }.isNotEmpty()) {
                    userRepositoryList.add(user.toUserRepository(repositoryList.filter { it.size > 1 }))
                }
            }
        } catch (e: Exception) {
            Log.e(this.toString(), "load fun ERROR:${e.message}")
        }

        val nextKey = if (userRepositoryList.size < loadSize && page < maxPage) null else page + 1

        val prevKey = if (page == 0) null else page - 1

        return LoadResult.Page(userRepositoryList, prevKey, nextKey)
    }
}
