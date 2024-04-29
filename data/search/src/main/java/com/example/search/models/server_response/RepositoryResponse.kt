package com.example.search.models.server_response

import com.example.search.models.Repository

data class RepositoryResponse(
    val default_branch: String,
    val html_url: String,
    val id: Long,
    val name: String,
    val size: Int,
)

fun RepositoryResponse.toRepository(): Repository {
    return Repository(
        id = this.id,
        name = this.name,
        defaultBranch = this.default_branch,
        htmlUrl = this.html_url,
    )
}
