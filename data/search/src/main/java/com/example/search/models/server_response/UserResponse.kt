package com.example.search.models.server_response

import com.example.search.models.UserRepository

data class UserResponse(
    val id: Long,
    val login: String,
    val avatar_url: String,
)

fun UserResponse.toUserRepository(repositoryList: List<RepositoryResponse>): UserRepository {
    return UserRepository(
        id = this.id,
        name = this.login,
        avatar = this.avatar_url,
        repository = repositoryList.map { it.toRepository() },
    )
}
