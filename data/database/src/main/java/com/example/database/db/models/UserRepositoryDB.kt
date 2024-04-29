package com.example.database.db.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.database.db.conteins.RepositoryConteins
import com.example.database.db.conteins.UserConteins

data class UserRepositoryDB(
    @Embedded
    val user: UserDB,
    @Relation(
        parentColumn = UserConteins.Colums.ID,
        entityColumn = RepositoryConteins.Colums.USER_ID,
    )
    val repository: List<RepositoryDB>,
)
