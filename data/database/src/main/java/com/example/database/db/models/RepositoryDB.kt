package com.example.database.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.database.db.conteins.RepositoryConteins
import com.example.database.db.conteins.UserConteins

@Entity(
    tableName = RepositoryConteins.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = UserDB::class,
            parentColumns = arrayOf(UserConteins.Colums.ID),
            childColumns = arrayOf(RepositoryConteins.Colums.USER_ID),
            onDelete = ForeignKey.CASCADE,
        ),
    ],
)
data class RepositoryDB(
    @PrimaryKey
    @ColumnInfo(name = RepositoryConteins.Colums.ID)
    val id: Long,
    @ColumnInfo(name = RepositoryConteins.Colums.USER_ID)
    val user_id: Long,
    @ColumnInfo(name = RepositoryConteins.Colums.NAME)
    val name: String,
    @ColumnInfo(name = RepositoryConteins.Colums.LINC)
    val linc: String,
)
