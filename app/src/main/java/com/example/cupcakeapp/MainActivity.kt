package com.example.cupcakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cupcakeapp.data.DataSource
import com.example.cupcakeapp.ui.StartOrderScreen
import com.example.cupcakeapp.ui.SelectOptionScreen
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeAppTheme {
                CupcakeNavigation()
            }
        }
    }
}

@Composable
fun CupcakeNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = { selectedQuantity ->
                    // Navegar a la pantalla de selección
                    navController.navigate("select_option")
                }
            )
        }
        composable("select_option") {
            val context = LocalContext.current
            SelectOptionScreen(
                onNavigateUp = { navController.popBackStack() },
                options = DataSource.flavors.map { id -> context.resources.getString(id) }
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    CupcakeAppTheme {
        CupcakeNavigation()
    }
}
