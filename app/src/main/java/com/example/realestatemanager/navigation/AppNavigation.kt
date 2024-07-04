package com.example.realestatemanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.realestatemanager.details.DetailsRoute
import com.example.realestatemanager.main.HomeRoute

@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route){
            HomeRoute()
        }
        composable(route = Screen.HomeScreen.route){
            DetailsRoute()
        }
    }

}