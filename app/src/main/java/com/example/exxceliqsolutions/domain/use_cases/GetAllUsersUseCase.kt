package com.example.exxceliqsolutions.domain.use_cases

import androidx.paging.PagingData
import com.example.exxceliqsolutions.data.repository.Repository
import com.example.exxceliqsolutions.domain.model.UserData
import kotlinx.coroutines.flow.Flow

class GetAllUsersUseCase (
    private val repository: Repository
) {
    //This method will fetch the data from the repository
    operator fun invoke(): Flow<PagingData<UserData>> {
        return repository.getAllHeroes()
    }
}