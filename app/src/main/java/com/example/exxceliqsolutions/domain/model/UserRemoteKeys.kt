package com.example.exxceliqsolutions.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exxceliqsolutions.presentation.util.Constants.USER_REMOTE_KEYS_DATABASE_TABLE

@Entity(tableName = USER_REMOTE_KEYS_DATABASE_TABLE)
data class UserRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val page: Int?,
    val totalPages: Int?,
)
