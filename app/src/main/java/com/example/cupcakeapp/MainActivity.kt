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
import com.example.cupcakeapp.ui.SummaryScreen

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
        // Pantalla inicial: seleccion de cantidad
        composable("start") {
            StartOrderScreen(
                quantityOptions = DataSource.quantityOptions,
                onNextButtonClicked = { selectedQuantity ->
                    navController.navigate("select_option/$selectedQuantity")
                }
            )
        }

        // Selección de sabor
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
                    val subtotal = quantityArg * 2.0 // ejemplo: cada cupcake cuesta 2
                    val flavorName = context.getString(DataSource.flavors[selectedFlavorIndex])
                    navController.navigate("pickup/$quantityArg/$flavorName/$subtotal")
                },
                onNavigateUp = { navController.popBackStack() }
            )
        }

        // Selección de fecha de pickup
        composable(
            route = "pickup/{quantity}/{flavor}/{subtotal}",
            arguments = listOf(
                navArgument("quantity") { type = NavType.IntType },
                navArgument("flavor") { type = NavType.StringType },
                navArgument("subtotal") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val quantity = backStackEntry.arguments?.getInt("quantity") ?: 0
            val flavor = backStackEntry.arguments?.getString("flavor") ?: ""
            val subtotal = backStackEntry.arguments?.getFloat("subtotal")?.toDouble() ?: 0.0

            PickupScreen(
                options = listOf("Hoy", "Mañana", "Próxima semana"),
                quantity = quantity,
                flavor = flavor,
                onNavigateUp = { navController.popBackStack() },
                onConfirmPickup = { date ->
                    navController.navigate("summary/$quantity/$flavor/$date/$subtotal")
                }
            )
        }

        // Resumen final
        composable(
            route = "summary/{quantity}/{flavor}/{date}/{subtotal}",
            arguments = listOf(
                navArgument("quantity") { type = NavType.IntType },
                navArgument("flavor") { type = NavType.StringType },
                navArgument("date") { type = NavType.StringType },
                navArgument("subtotal") { type = NavType.FloatType }
            )
        ) { backStackEntry ->
            val quantity = backStackEntry.arguments?.getInt("quantity") ?: 0
            val flavor = backStackEntry.arguments?.getString("flavor") ?: ""
            val date = backStackEntry.arguments?.getString("date") ?: ""
            val subtotal = backStackEntry.arguments?.getFloat("subtotal")?.toDouble() ?: 0.0

            SummaryScreen(
                quantity = quantity,
                flavor = flavor,
                date = date,
                subtotal = subtotal,
                onConfirm = {
                    navController.popBackStack("start", inclusive = false)
                }
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
