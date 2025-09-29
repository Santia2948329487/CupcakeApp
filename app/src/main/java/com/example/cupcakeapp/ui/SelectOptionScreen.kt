package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cupcakeapp.R
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOptionScreen(
    options: List<String>,
    quantity: Int,
    onSelectionChanged: (String) -> Unit = {},
    onNextButtonClicked: (selectedIndex: Int) -> Unit = {},
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {}
) {
    // índice seleccionado (-1 = ninguno)
    var selectedIndex by rememberSaveable { mutableStateOf(-1) }

    // Precio fijo por cupcake (ajusta si quieres)
    val pricePerCupcake = 2
    val subtotal = if (selectedIndex >= 0) quantity * pricePerCupcake else 0

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.choose_flavor)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back_button)
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(dimensionResource(R.dimen.padding_medium)),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                options.forEachIndexed { idx, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectable(
                                selected = (selectedIndex == idx),
                                onClick = {
                                    selectedIndex = idx
                                    onSelectionChanged(item)
                                }
                            )
                            .padding(vertical = dimensionResource(R.dimen.padding_small)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (selectedIndex == idx),
                            onClick = {
                                selectedIndex = idx
                                onSelectionChanged(item)
                            }
                        )

                        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.padding_small)))

                        Text(
                            text = item,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }

                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_medium)))

                // Subtotal en tiempo real
                Text(
                    text = stringResource(R.string.subtotal_price, subtotal),
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            // Botón Next en la parte inferior
            Button(
                onClick = {
                    if (selectedIndex >= 0) {
                        onNextButtonClicked(selectedIndex)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = dimensionResource(R.dimen.padding_small)),
                enabled = selectedIndex >= 0
            ) {
                Text(text = stringResource(R.string.next_button))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SelectOptionPreview() {
    CupcakeAppTheme {
        SelectOptionScreen(
            options = listOf("Vanilla", "Chocolate", "Red Velvet"),
            quantity = 6,
            onSelectionChanged = {},
            onNextButtonClicked = {}
        )
    }
}
