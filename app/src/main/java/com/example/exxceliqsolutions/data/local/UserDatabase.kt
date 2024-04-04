package com.example.exxceliqsolutions.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.exxceliqsolutions.data.local.dao.UserRemoteKeysDao
import com.example.exxceliqsolutions.data.local.dao.UserDao
import com.example.exxceliqsolutions.domain.model.UserData
import com.example.exxceliqsolutions.domain.model.UserRemoteKeys

@Database(entities = [UserData::class , UserRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun remoteKeyDao(): UserRemoteKeysDao
}