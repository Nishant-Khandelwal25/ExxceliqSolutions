package com.example.exxceliqsolutions.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.exxceliqsolutions.domain.use_cases.UseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    useCase: UseCase
) : ViewModel() {

    //This will fetch the data from use case and will store it in a flow which will be accessed by the UI
    val getAllUsers = useCase.getAllUsersUseCase.invoke().cachedIn(viewModelScope)
}