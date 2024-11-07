package com.example.search.models.server_response

import com.example.database.db.models.UserDB
import com.example.search.models.UserRepository

data class UserResponse(
    val id: Long,
    val login: String,
    val avatar_url: String,
)

fun UserResponse.toUserDB(): UserDB =
    UserDB(
        id = this.id,
        name = this.login,
        avatar = this.avatar_url,
    )

fun UserResponse.toUserRepository(repositoryList: List<RepositoryResponse>): UserRepository =
    UserRepository(
        id = this.id,
        name = this.login,
        avatar = this.avatar_url,
        repository = repositoryList.map { it.toRepository() },
    )
