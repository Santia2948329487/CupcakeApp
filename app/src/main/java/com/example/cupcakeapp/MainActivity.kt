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
import com.example.cupcakeapp.ui.PickupScreen


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
        // start: seleccion de cantidad
        composable("start") {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = { selectedQuantity ->
                    // navegamos y pasamos la cantidad como argumento
                    navController.navigate("select_option/$selectedQuantity")
                }
            )
        }

        // select_option espera {quantity}
        composable(
            route = "select_option/{quantity}",
            arguments = listOf(navArgument("quantity") { type = NavType.IntType })
        ) { backStackEntry ->
            val context = LocalContext.current
            val quantityArg = backStackEntry.arguments?.getInt("quantity") ?: 1

            SelectOptionScreen(
                options = DataSource.flavors.map { id -> context.getString(id) },
                quantity = quantityArg,
                onSelectionChanged = { /* opcional */ },
                onNextButtonClicked = { selectedFlavorIndex ->
                    // navegamos a pickup pasando la cantidad y el index del sabor
                    navController.navigate("pickup/$quantityArg/$selectedFlavorIndex")
                },
                onNavigateUp = { navController.popBackStack() }
            )
        }

        // ruta pickup con ARGs (cantidad y flavorIndex)
        composable(
            route = "pickup/{quantity}/{flavorIndex}",
            arguments = listOf(
                navArgument("quantity") { type = NavType.IntType },
                navArgument("flavorIndex") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val context = LocalContext.current
            val quantity = backStackEntry.arguments?.getInt("quantity") ?: 1
            val flavorIndex = backStackEntry.arguments?.getInt("flavorIndex") ?: 0
            // convertimos flavorIndex a string usando DataSource
            val flavorName = DataSource.flavors.getOrNull(flavorIndex)?.let { resId -> context.getString(resId) } ?: ""

            // Construye la lista de opciones de pickup (ejemplo)
            val pickupOptions = DataSource.pickupOptions.map { id -> context.getString(id) }

            // Llamamos a PickupScreen (implementa a tu gusto)
            PickupScreen(
                options = pickupOptions,
                quantity = quantity,
                flavor = flavorName,
                onNavigateUp = { navController.popBackStack() },
                onConfirmPickup = { selectedPickupIndex ->
                    // ejemplo: navegar a summary (si tienes summary)
                    navController.navigate("summary/$quantity/$flavorIndex/$selectedPickupIndex")
                }
            )
        }

        // ... (puedes agregar la ruta "summary/..." aquí si ya la implementaste)
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainPreview() {
    CupcakeAppTheme {
        CupcakeNavigation()
    }
}
