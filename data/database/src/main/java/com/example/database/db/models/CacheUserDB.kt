package com.example.database.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.db.conteins.CacheUserConteins

@Entity(
    tableName = CacheUserConteins.TABLE_NAME,
)
data class CacheUserDB(
    @PrimaryKey
    @ColumnInfo(name = CacheUserConteins.Colums.ID)
    val id: Long,
    @ColumnInfo(name = CacheUserConteins.Colums.NAME)
    val name: String,
    @ColumnInfo(name = CacheUserConteins.Colums.AVATAR)
    val avatar: String,
)
