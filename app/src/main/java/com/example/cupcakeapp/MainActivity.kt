package com.example.cupcakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.navArgument


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
        startDestination = "start",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("start") {
            // Cuando el usuario elige cantidad, navegamos y pasamos la cantidad en la ruta
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = { selectedQuantity ->
                    navController.navigate("select_option/$selectedQuantity")
                }
            )
        }

        // Definimos la ruta con un argumento {quantity}
        composable(
            route = "select_option/{quantity}",
            arguments = listOf(navArgument("quantity") { type = NavType.IntType })
        ) { backStackEntry ->
            val context = LocalContext.current
            // Obtenemos la cantidad enviada en la ruta
            val quantityArg = backStackEntry.arguments?.getInt("quantity") ?: 1

            SelectOptionScreen(
                quantity = quantityArg, // <-- ahora sí le pasamos la cantidad real
                options = DataSource.flavors.map { id -> context.getString(id) },
                onSelectionChanged = { /* guardar sabor si hace falta */ },
                onNavigateUp = { navController.popBackStack() }
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
