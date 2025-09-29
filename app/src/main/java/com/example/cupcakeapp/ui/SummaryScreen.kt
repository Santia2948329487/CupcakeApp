package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcakeapp.R
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SummaryScreen(
    quantity: Int,
    flavor: String,
    date: String,
    subtotal: Double,
    onConfirm: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Order Summary") }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_medium))
        ) {
            Text("Quantity: $quantity", style = MaterialTheme.typography.bodyLarge)
            Text("Flavor: $flavor", style = MaterialTheme.typography.bodyLarge)
            Text("Pickup Date: $date", style = MaterialTheme.typography.bodyLarge)
            Text("Subtotal: $${"%.2f".format(subtotal)}", style = MaterialTheme.typography.bodyLarge)

            Button(
                onClick = onConfirm,
                modifier = Modifier.padding(top = dimensionResource(R.dimen.padding_medium))
            ) {
                Text("Confirm Order")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SummaryPreview() {
    CupcakeAppTheme {
        SummaryScreen(
            quantity = 6,
            flavor = "Chocolate",
            date = "Today",
            subtotal = 12.0,
            onConfirm = {}
        )
    }
}
