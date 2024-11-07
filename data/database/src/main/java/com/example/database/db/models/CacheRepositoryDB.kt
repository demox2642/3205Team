package com.example.database.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.database.db.conteins.CacheRepositoryConteins
import com.example.database.db.conteins.CacheUserConteins

@Entity(
    tableName = CacheRepositoryConteins.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = CacheUserDB::class,
            parentColumns = arrayOf(CacheUserConteins.Colums.ID),
            childColumns = arrayOf(CacheRepositoryConteins.Colums.USER_ID),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class CacheRepositoryDB(
    @PrimaryKey
    @ColumnInfo(name = CacheRepositoryConteins.Colums.ID)
    val id: Long,
    @ColumnInfo(name = CacheRepositoryConteins.Colums.USER_ID)
    val user_id: Long,
    @ColumnInfo(name = CacheRepositoryConteins.Colums.NAME)
    val name: String,
    @ColumnInfo(name = CacheRepositoryConteins.Colums.LINC)
    val linc: String,
)
