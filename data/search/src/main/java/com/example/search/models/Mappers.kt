package com.example.search.models

import com.example.database.db.models.RepositoryDB
import com.example.database.db.models.UserDB

fun User.toUserDB(): UserDB {
    return UserDB(
        id,
        name,
        avatar,
    )
}

fun Repository.toRepositoryDB(userId: Long): RepositoryDB  {
    return RepositoryDB(id, user_id = userId, name, htmlUrl)
}
