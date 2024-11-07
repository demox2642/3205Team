package com.example.database.db.models

import androidx.room.Embedded
import androidx.room.Relation
import com.example.database.db.conteins.CacheRepositoryConteins
import com.example.database.db.conteins.CacheUserConteins

data class CacheUserRepositoryDB(
    @Embedded
    val user: CacheUserDB,
    @Relation(
        parentColumn = CacheUserConteins.Colums.ID,
        entityColumn = CacheRepositoryConteins.Colums.USER_ID,
    )
    val repository: List<CacheRepositoryDB>,
)
