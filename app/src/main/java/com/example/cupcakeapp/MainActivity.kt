package com.example.cupcakeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.cupcakeapp.CupcakeApp
import com.example.cupcakeapp.ui.theme.CupcakeAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CupcakeAppTheme {
                CupcakeApp()
            }
        }
    }
}
