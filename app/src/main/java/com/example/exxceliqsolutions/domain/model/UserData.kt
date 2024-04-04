package com.example.exxceliqsolutions.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.exxceliqsolutions.presentation.util.Constants.USER_DATABASE_TABLE

@Entity(tableName = USER_DATABASE_TABLE)
data class UserData(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val avatar: String,
    val email: String,
    val firstName: String,
    val lastName: String
)