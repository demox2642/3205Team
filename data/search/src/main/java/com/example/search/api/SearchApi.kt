package com.example.search.api

import com.example.database.network.models.PagingServerResponse
import com.example.search.models.server_response.RepositoryResponse
import com.example.search.models.server_response.UserResponse
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SearchApi {
    @GET("search/users")
    suspend fun getUsers(
        @Query("q") searchText: String,
        @Query("per_page") pageSize: Int,
        @Query("page") pageNum: Int,
    ): PagingServerResponse<UserResponse>


    @GET("users/{user}/repos")
    suspend fun getRepository(
        @Path("user") userName: String,
    ): List<RepositoryResponse>
}
