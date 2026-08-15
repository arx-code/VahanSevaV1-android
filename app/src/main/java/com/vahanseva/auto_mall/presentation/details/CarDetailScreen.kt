package com.vahanseva.auto_mall.presentation.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.vahanseva.auto_mall.presentation.viewmodel.CarDetailViewModel
import com.vahanseva.auto_mall.presentation.viewmodel.CarDetailUiState
import com.vahanseva.auto_mall.presentation.theme.Spacing
import com.vahanseva.auto_mall.presentation.components.details.ImageCarousel
import com.vahanseva.auto_mall.presentation.components.details.SellerCard

@Composable
fun CarDetailScreen(
    onNavigateBack: () -> Unit,
    onContactSeller: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    viewModel: CarDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    val car by viewModel.car.collectAsState()
    val isFavorite by viewModel.isFavorite.collectAsState()

    Box(modifier = modifier.fillMaxSize()) {
        when (uiState) {
            is CarDetailUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is CarDetailUiState.Error -> {
                Text(
                    text = (uiState as CarDetailUiState.Error).message,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            is CarDetailUiState.Success -> {
                car?.let {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState())
                            .padding(bottom = Spacing.md)
                    ) {
                        ImageCarousel(images = it.images)
                        
                        Column(modifier = Modifier.padding(Spacing.md)) {
                            Text(text = "${it.brand} ${it.model}", style = MaterialTheme.typography.headlineMedium)
                            Text(text = "Year: ${it.year}", style = MaterialTheme.typography.bodyLarge)
                            Text(text = "Price: $${it.price}", style = MaterialTheme.typography.headlineSmall, color = MaterialTheme.colorScheme.primary)
                            
                            Spacer(modifier = Modifier.height(Spacing.md))
                            
                            SellerCard(
                                seller = it.seller,
                                onContactClick = { onContactSeller(it.seller.id) }
                            )
                        }
                    }
                }
            }
        }
    }
}
