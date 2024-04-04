package com.example.exxceliqsolutions.data.repository

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.exxceliqsolutions.data.local.UserDatabase
import com.example.exxceliqsolutions.data.network.ApiService
import com.example.exxceliqsolutions.data.paging_source.UserRemoteMediator
import com.example.exxceliqsolutions.domain.model.UserData
import com.example.exxceliqsolutions.domain.repository.RemoteDataSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val apiService: ApiService,
    private val userDatabase: UserDatabase,
    @ApplicationContext val appContext: Context,
) : RemoteDataSource {

    private val userDao = userDatabase.userDao()

    //This method will fetch the user data from the remote mediator
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllUsers(): Flow<PagingData<UserData>> {
        val pagingSourceFactory = {userDao.getAllUsers()}
        return Pager(
            config = PagingConfig(pageSize = 6),
            remoteMediator = UserRemoteMediator(apiService, userDatabase, appContext),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}