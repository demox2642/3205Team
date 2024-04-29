package com.example.database.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.db.conteins.UserConteins
import com.example.database.db.models.UserDB
import com.example.database.db.models.UserRepositoryDB

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(user: UserDB)

    @Delete
    fun deleteUser(user: UserDB)

    @Query("SELECT * FROM ${UserConteins.TABLE_NAME}")
    fun getAllUsers(): List<UserDB>

    @Query("SELECT * FROM ${UserConteins.TABLE_NAME}")
    fun getUsersRepository(): List<UserRepositoryDB>
}
