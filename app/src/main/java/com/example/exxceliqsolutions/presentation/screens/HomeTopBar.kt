package com.example.exxceliqsolutions.presentation.screens

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.exxceliqsolutions.R
import com.example.exxceliqsolutions.ui.theme.topAppBarBackgroundColor
import com.example.exxceliqsolutions.ui.theme.topAppBarContentColor

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopBar() {
    TopAppBar(
        title = {
            Text(text = stringResource(R.string.home_screen), color = topAppBarContentColor)
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topAppBarBackgroundColor
        )
    )
}