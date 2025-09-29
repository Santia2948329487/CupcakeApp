package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PickupScreen(
    options: List<String>,
    quantity: Int,
    flavor: String,
    onNavigateUp: () -> Unit = {},
    onConfirmPickup: (selectedPickupIndex: Int) -> Unit = {},
    modifier: Modifier = Modifier
) {
    var selectedIndex by remember { mutableStateOf(-1) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Pickup date") }, navigationIcon = {
                IconButton(onClick = onNavigateUp) { Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back") }
            })
        }
    ) { innerPadding ->
        Column(modifier = modifier.padding(innerPadding).padding(16.dp)) {
            Text(text = "Sabor: $flavor")
            Text(text = "Cantidad: $quantity")
            Spacer(modifier = Modifier.height(8.dp))
            LazyColumn {
                itemsIndexed(options) { idx, option ->
                    Row(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                        RadioButton(selected = selectedIndex == idx, onClick = { selectedIndex = idx })
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option)
                    }
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { if (selectedIndex >= 0) onConfirmPickup(selectedIndex) },
                enabled = selectedIndex >= 0,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Confirm pickup")
            }
        }
    }
}
