package com.example.exxceliqsolutions.data.repository

import androidx.paging.PagingData
import com.example.exxceliqsolutions.domain.model.UserData
import com.example.exxceliqsolutions.domain.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class Repository @Inject constructor(
    private val remote: RemoteDataSource,
) {

    //This method will fetch data from the remote data source and use case will use this
    fun getAllHeroes(): Flow<PagingData<UserData>> {
        return remote.getAllUsers()
    }
}