package com.example.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.db.dao.CacheRepositoryDAO
import com.example.database.db.dao.CacheUserDAO
import com.example.database.db.dao.RemoteKeysDAO
import com.example.database.db.dao.RepositoryDAO
import com.example.database.db.dao.UserDAO
import com.example.database.db.models.CacheRepositoryDB
import com.example.database.db.models.CacheUserDB
import com.example.database.db.models.RemoteKeysDB
import com.example.database.db.models.RepositoryDB
import com.example.database.db.models.UserDB

@Database(
    entities = [
        UserDB::class,
        RepositoryDB::class,
        CacheUserDB::class,
        CacheRepositoryDB::class,
        RemoteKeysDB::class,
    ],
    version = 2,
)
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDAO

    abstract fun repositoryDao(): RepositoryDAO

    abstract fun cacheUserDao(): CacheUserDAO

    abstract fun cacheRepositoryDao(): CacheRepositoryDAO

    abstract fun remoteKeyDao(): RemoteKeysDAO
}
