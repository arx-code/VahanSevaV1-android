package com.vahanseva.auto_mall

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.vahanseva.auto_mall.presentation.navigation.VahanSevaNavigation
import com.vahanseva.auto_mall.ui.theme.VahanSevaAutoMallTheme
import dagger.hilt.android.AndroidEntryPoint

/**
 * Main Activity for VahanSeva App
 * Annotated with @AndroidEntryPoint to enable Hilt dependency injection
 */
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VahanSevaAutoMallTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    VahanSevaNavigation()
                }
            }
        }
    }
}
