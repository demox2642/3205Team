package com.example.database.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.db.conteins.UserConteins

@Entity(
    tableName = UserConteins.TABLE_NAME,
)
data class UserDB(
    @PrimaryKey
    @ColumnInfo(name = UserConteins.Colums.ID)
    val id: Long,
    @ColumnInfo(name = UserConteins.Colums.NAME)
    val name: String,
    @ColumnInfo(name = UserConteins.Colums.AVATAR)
    val avatar: String,
)
