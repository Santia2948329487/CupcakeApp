package com.example.cupcakeapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cupcakeapp.R
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SelectOptionScreen(
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit = {} // valor por defecto
) {
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
                }
            )
        }
    ) { innerPadding ->
        Text(
            text = stringResource(R.string.choose_flavor),
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
@Preview(showBackground = true)
@Composable
fun SelectOptionPreview() {
    CupcakeAppTheme {
        SelectOptionScreen()
    }
}
