package com.vahanseva.auto_mall.presentation.components.details

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vahanseva.auto_mall.data.model.User
import com.vahanseva.auto_mall.presentation.theme.Spacing

@Composable
fun SellerCard(
    seller: User,
    onContactClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(Spacing.sm),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(Spacing.md)) {
            Text(text = "Seller Information", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(Spacing.sm))
            Text(text = "Name: ${seller.name}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Location: ${seller.location}", style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(Spacing.md))
            Button(
                onClick = onContactClick,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Contact Seller")
            }
        }
    }
}
