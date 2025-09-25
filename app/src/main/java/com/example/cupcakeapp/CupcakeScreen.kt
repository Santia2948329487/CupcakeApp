package com.example.cupcakeapp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CupcakeScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Cupcake App") }
            )
        },
        content = { innerPadding ->
            Text(
                text = "Pantalla principal",
                modifier = Modifier.fillMaxSize()
            )
        }
    )
}
