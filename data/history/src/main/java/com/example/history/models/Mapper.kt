package com.example.history.models

import com.example.database.db.models.CacheUserRepositoryDB

fun CacheUserRepositoryDB.toHistoryUserRepo(): List<HistoryUserRepo> {
    val historyUserRepoList = mutableListOf<HistoryUserRepo>()
    this.repository.forEach { repo ->
        historyUserRepoList.add(
            HistoryUserRepo(
                name = this.user.name,
                avatar = this.user.avatar,
                repoName = repo.name,
                repoLinc = repo.linc,
            ),
        )
    }
    return historyUserRepoList
}
