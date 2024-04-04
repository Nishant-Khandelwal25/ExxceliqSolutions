package com.example.exxceliqsolutions.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.exxceliqsolutions.domain.model.UserData

@Dao
interface UserDao {

    @Query("SELECT * from user_table order by id ASC")
    fun getAllUsers(): PagingSource<Int, UserData>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(users: List<UserData>)

    @Query("delete from user_table")
    suspend fun deleteAllUsers()
}