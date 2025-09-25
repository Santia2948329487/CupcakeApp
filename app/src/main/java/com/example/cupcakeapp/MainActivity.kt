package com.example.cupcakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme
import com.example.cupcakeapp.ui.StartOrderScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeAppTheme {
                // Llamamos a la nueva pantalla de inicio del pedido
                StartOrderScreen()
            }
        }
    }
}
