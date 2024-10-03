package com.example.realestatemanager.feature

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.ui.platform.LocalContext
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.google.android.gms.maps.MapsInitializer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            MapsInitializer.initialize(context)
            RealEstateManagerTheme {
                val widthSizeClass = calculateWindowSizeClass(this).widthSizeClass
                RealEstateManagerApp(widthSizeClass)
            }
        }
    }
}