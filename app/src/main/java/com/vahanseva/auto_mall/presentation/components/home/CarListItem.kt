package com.vahanseva.auto_mall.presentation.components.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.vahanseva.auto_mall.data.model.Car
import com.vahanseva.auto_mall.presentation.theme.Spacing

@Composable
fun CarListItem(
    car: Car,
    onClick: (Car) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.sm)
            .clickable { onClick(car) },
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(Spacing.md)) {
            Text(text = "${car.brand} ${car.model}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Year: ${car.year}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Price: $${car.price}", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
        }
    }
}
