package com.example.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.database.db.conteins.CacheRepositoryConteins
import com.example.database.db.models.CacheRepositoryDB

@Dao
interface CacheRepositoryDAO {
    @Insert
    fun saveRepository(repositoryDB: CacheRepositoryDB)

    @Insert
    fun saveAllRepository(repositoryDB: List<CacheRepositoryDB>)

    @Query("Delete FROM ${CacheRepositoryConteins.TABLE_NAME}")
    fun clearAllRepository()
}
