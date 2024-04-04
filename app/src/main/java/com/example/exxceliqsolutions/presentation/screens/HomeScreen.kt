package com.example.exxceliqsolutions.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.example.exxceliqsolutions.presentation.components.ListContent
import com.example.exxceliqsolutions.presentation.view_model.HomeViewModel


@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val allUsers = viewModel.getAllUsers.collectAsLazyPagingItems()


    Scaffold(topBar = {
        HomeTopBar()
    },
        content = {
            Column(Modifier.padding(it)) {
                ListContent(
                    users = allUsers,
                )
            }
        })
}