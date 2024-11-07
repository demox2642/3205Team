package com.example.search.models

import com.example.database.db.models.CacheRepositoryDB
import com.example.database.db.models.CacheUserDB
import com.example.database.db.models.RepositoryDB
import com.example.database.db.models.UserDB
import com.example.database.db.models.UserRepositoryDB

fun User.toUserDB(): UserDB =
    UserDB(
        id,
        name,
        avatar,
    )

fun User.toCacheUserDB(): CacheUserDB =
    CacheUserDB(
        id,
        name,
        avatar,
    )

fun Repository.toRepositoryDB(userId: Long): RepositoryDB = RepositoryDB(id, user_id = userId, name, htmlUrl)

fun Repository.toCacheRepositoryDB(userId: Long): CacheRepositoryDB = CacheRepositoryDB(id, user_id = userId, name, htmlUrl)

fun List<UserRepository>.toListRepositoryDB(): List<RepositoryDB> {
    val listRepository = mutableListOf<RepositoryDB>()
    this.forEach { userRepository ->
        listRepository.addAll(
            userRepository.repository.map { it.toRepositoryDB(userRepository.id) },
        )
    }

    return listRepository.toList()
}

fun RepositoryDB.toRepository() = Repository(id, name, defaultBranch = "main", htmlUrl = linc)

fun UserRepositoryDB.toUserRepository() =
    UserRepository(
        id = this.user.id,
        name = this.user.name,
        avatar = this.user.avatar,
        repository = this.repository.map { it.toRepository() },
    )
