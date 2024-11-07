package com.example.database.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.db.conteins.CacheUserConteins
import com.example.database.db.models.CacheUserDB
import com.example.database.db.models.CacheUserRepositoryDB

@Dao
interface CacheUserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: CacheUserDB)

    @Delete
    fun deleteUser(user: CacheUserDB)

    @Query("SELECT * FROM ${CacheUserConteins.TABLE_NAME}")
    fun getAllUsers(): List<CacheUserDB>

    @Query("SELECT * FROM ${CacheUserConteins.TABLE_NAME} ")
    fun getUsersRepository(): List<CacheUserRepositoryDB>

    @Query("DELETE FROM ${CacheUserConteins.TABLE_NAME}")
    fun clearAllUser()
}
