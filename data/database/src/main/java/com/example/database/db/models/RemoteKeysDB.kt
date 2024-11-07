package com.example.database.db.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.database.db.conteins.RemoteKeysContents

@Entity(tableName = RemoteKeysContents.TABLE_NAME)
data class RemoteKeysDB(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = RemoteKeysContents.Colums.USER_ID)
    val userID: Long,
    @ColumnInfo(name = RemoteKeysContents.Colums.PREV_KEY)
    val prevKey: Int?,
    @ColumnInfo(name = RemoteKeysContents.Colums.CURRENT_PAGE)
    val currentPage: Int,
    @ColumnInfo(name = RemoteKeysContents.Colums.NEXT_KEY)
    val nextKey: Int?,
    @ColumnInfo(name = RemoteKeysContents.Colums.CREATE_AT)
    val createdAt: Long = System.currentTimeMillis(),
)
