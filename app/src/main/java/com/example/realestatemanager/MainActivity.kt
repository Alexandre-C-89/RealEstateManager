package com.example.realestatemanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RealEstateManagerTheme {
                AppNavigation()
            }
        }
    }
}