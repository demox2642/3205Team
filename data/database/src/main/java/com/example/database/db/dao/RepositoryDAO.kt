package com.example.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.database.db.conteins.RepositoryConteins
import com.example.database.db.models.RepositoryDB

@Dao
interface RepositoryDAO {
    @Insert
    fun saveRepository(repositoryDB: RepositoryDB)

    @Insert
    fun saveAllRepository(repositoryDB: List<RepositoryDB>)

    @Query("Delete FROM ${RepositoryConteins.TABLE_NAME}")
    fun clearAllRepository()
}
