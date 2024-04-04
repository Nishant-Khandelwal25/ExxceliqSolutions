package com.example.exxceliqsolutions.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemKey
import androidx.wear.compose.material.ContentAlpha.medium
import coil.compose.rememberAsyncImagePainter
import com.example.exxceliqsolutions.R
import com.example.exxceliqsolutions.domain.model.UserData
import com.example.exxceliqsolutions.ui.theme.LARGE_PADDING
import com.example.exxceliqsolutions.ui.theme.MEDIUM_PADDING
import com.example.exxceliqsolutions.ui.theme.SMALL_PADDING
import com.example.exxceliqsolutions.ui.theme.USER_ITEM_HEIGHT
import com.example.exxceliqsolutions.ui.theme.topAppBarContentColor

@Composable
fun ListContent(
    users: LazyPagingItems<UserData>
) {
    val result = handlePagingResult(heroes = users)

    if (result) {
        LazyColumn(
            contentPadding = PaddingValues(SMALL_PADDING),
            verticalArrangement = spacedBy(SMALL_PADDING)
        ) {
            items(
                key = users.itemKey { it.id },
                count = users.itemCount
            ) {
                val hero = users[it]
                hero?.let { user ->
                    HeroItem(user = user)
                }
            }
        }
    }

}

@Composable
fun handlePagingResult(heroes: LazyPagingItems<UserData>): Boolean {
    heroes.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            else -> null
        }

        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null -> {
                EmptyScreen(error = error)
                false
            }

            heroes.itemCount < 1 -> {
                EmptyScreen()
                false
            }

            else -> true

        }
    }
}

@Composable
fun HeroItem(
    user: UserData
) {

    val painter = rememberAsyncImagePainter(
        user.avatar,
        placeholder = painterResource(id = R.drawable.placeholder),
        onError = {
            R.drawable.placeholder
        })

    Box(
        modifier = Modifier
            .height(USER_ITEM_HEIGHT), contentAlignment = Alignment.BottomStart
    ) {
        Surface(shape = RoundedCornerShape(LARGE_PADDING)) {
            Image(
                painter = painter,
                contentDescription = "User Avatar",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Surface(
            modifier = Modifier
                .fillMaxHeight(0.2f)
                .fillMaxWidth(),
            color = Color.Black.copy(alpha = medium),
            shape = RoundedCornerShape(bottomEnd = 0.dp, bottomStart = 0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(MEDIUM_PADDING)
            ) {
                Text(
                    text = user.firstName,
                    color = topAppBarContentColor,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.padding(SMALL_PADDING))

                Text(
                    text = user.lastName,
                    color = topAppBarContentColor,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

            }
        }

    }
}