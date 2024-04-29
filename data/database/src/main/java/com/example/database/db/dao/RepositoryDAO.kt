package com.example.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.database.db.models.RepositoryDB

@Dao
interface RepositoryDAO {
    @Insert
    fun saveRepository(repositoryDB: RepositoryDB)

//    @Query("SELECT * FROM ${UserConteins.TABLE_NAME}")
//    fun getAllRepository(): LiveData<List<RepositoryDB>>
}
