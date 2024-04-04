package com.example.exxceliqsolutions.domain.repository

import androidx.paging.PagingData
import com.example.exxceliqsolutions.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {
    fun getAllUsers() : Flow<PagingData<UserData>>
}