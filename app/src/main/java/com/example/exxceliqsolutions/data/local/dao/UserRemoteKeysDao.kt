package com.example.exxceliqsolutions.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exxceliqsolutions.domain.model.UserRemoteKeys

@Dao
interface UserRemoteKeysDao {

    @Query("select * from user_remote_keys_table where id = :id")
    suspend fun getRemoteKeys(id: Int): UserRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(userRemoteKeys: List<UserRemoteKeys>)

    @Query("delete from user_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}