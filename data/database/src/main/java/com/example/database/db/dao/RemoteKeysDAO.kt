package com.example.database.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.database.db.conteins.RemoteKeysContents
import com.example.database.db.models.RemoteKeysDB

@Dao
interface RemoteKeysDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKeysDB>)

    @Query("SELECT * FROM ${RemoteKeysContents.TABLE_NAME} Where ${RemoteKeysContents.Colums.USER_ID} = :id")
    fun getRemoteKeyByUserID(id: Long): RemoteKeysDB?

    @Query("Delete FROM ${RemoteKeysContents.TABLE_NAME}")
    fun clearRemoteKeys()

    @Query(
        "SELECT  ${RemoteKeysContents.Colums.CREATE_AT} FROM  ${RemoteKeysContents.TABLE_NAME} Order By ${RemoteKeysContents.Colums.CREATE_AT} DESC LIMIT 1",
    )
    fun getCreationTime(): Long?
}
