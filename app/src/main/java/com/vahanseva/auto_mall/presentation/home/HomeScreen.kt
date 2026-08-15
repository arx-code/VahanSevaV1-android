package com.vahanseva.auto_mall.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.vahanseva.auto_mall.presentation.components.empty.EmptyState
import com.vahanseva.auto_mall.presentation.components.home.CarListItem
import com.vahanseva.auto_mall.presentation.viewmodel.CarListUiState
import com.vahanseva.auto_mall.presentation.viewmodel.CarListViewModel

/**
 * Home screen - Discovery experience
 * Displays paginated car listings
 */
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: CarListViewModel = hiltViewModel(),
    onCarClick: (String) -> Unit = {}
) {
    val pagingItems = viewModel.cars.collectAsLazyPagingItems()
    val uiState by viewModel.uiState.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when {
            uiState is CarListUiState.Loading && pagingItems.itemCount == 0 -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            uiState is CarListUiState.Error && pagingItems.itemCount == 0 -> {
                EmptyState(
                    icon = Icons.Default.Error,
                    title = "Error",
                    message = (uiState as CarListUiState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            pagingItems.itemCount == 0 -> {
                EmptyState(
                    icon = Icons.Default.Search,
                    title = "No cars found",
                    message = "Try adjusting your search filters",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            else -> {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(
                        count = pagingItems.itemCount,
                        key = { index -> pagingItems[index]?.id ?: index }
                    ) { index ->
                        val car = pagingItems[index]
                        car?.let {
                            CarListItem(
                                car = it,
                                onClick = { clickedCar -> onCarClick(clickedCar.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
