package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.db.dao.RepositoryDAO
import com.example.database.db.dao.UserDAO
import com.example.database.db.models.RepositoryDB
import com.example.database.db.models.UserDB

@Database(
    entities = [UserDB::class, RepositoryDB::class],
    version = 1,
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun repositoryDao(): RepositoryDAO
}
