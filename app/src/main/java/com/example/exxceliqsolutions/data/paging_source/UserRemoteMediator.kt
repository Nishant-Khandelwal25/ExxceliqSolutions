package com.example.exxceliqsolutions.data.paging_source

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.exxceliqsolutions.data.local.UserDatabase
import com.example.exxceliqsolutions.data.mapper.toApiResponseData
import com.example.exxceliqsolutions.data.network.ApiService
import com.example.exxceliqsolutions.data.util.isInternetAvailable
import com.example.exxceliqsolutions.domain.model.ApiResponseData
import com.example.exxceliqsolutions.domain.model.UserData
import com.example.exxceliqsolutions.domain.model.UserRemoteKeys
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


/**
 * This class will be responsible for handling pagination and it contains the logic to increase the page number
 * as the user scrolls through the list. It's also responsible for caching data into room db so the use can access
 * the data even when the internet is off.
 */
@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator @Inject constructor(
    private val apiService: ApiService,
    private val userDatabase: UserDatabase,
    @ApplicationContext val appContext: Context,
) : RemoteMediator<Int, UserData>() {

    private val userDao = userDatabase.userDao()
    private val remoteKeyDao = userDatabase.remoteKeyDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserData>
    ): MediatorResult {
        return try {
            if (isInternetAvailable(appContext)) {
                val page = when (loadType) {
                    LoadType.REFRESH -> {
                        1
                    }

                    LoadType.PREPEND -> {
                        return MediatorResult.Success(endOfPaginationReached = true)
                    }

                    LoadType.APPEND -> {
                        val remoteKeys = remoteKeyForLastItem(state)
                        val nextPage = remoteKeys?.totalPages ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                        nextPage
                    }
                }
                val response = apiService.getUsers(page = page)
                lateinit var userData: ApiResponseData
                if (response.data?.isNotEmpty() == true) {
                    response.let {
                        userData = it.toApiResponseData()
                    }
                }

                if (response.data?.isNotEmpty() == true) {
                    userDatabase.withTransaction {
                        if (loadType == LoadType.REFRESH) {
                            userDao.deleteAllUsers()
                            remoteKeyDao.deleteAllRemoteKeys()
                        }

                        val page = userData.page
                        val totalPages = userData.totalPages

                        val keys = userData.data.map { user ->
                            UserRemoteKeys(
                                id = user.id,
                                page = page,
                                totalPages = totalPages,
                            )
                        }

                        remoteKeyDao.addAllRemoteKeys(keys)
                        userDao.insertUser(userData.data)
                    }
                }
                MediatorResult.Success(endOfPaginationReached = response.page == 2)
            } else {
                MediatorResult.Success(endOfPaginationReached = true)
            }
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun remoteKeyForLastItem(state: PagingState<Int, UserData>): UserRemoteKeys? {
        return state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let { hero ->
            remoteKeyDao.getRemoteKeys(hero.id)
        }
    }
}