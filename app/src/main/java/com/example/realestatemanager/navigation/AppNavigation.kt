package com.example.realestatemanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.realestatemanager.details.DetailsRoute
import com.example.realestatemanager.edit.EditRoute
import com.example.realestatemanager.main.HomeRoute

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.HomeRoute.route
    ) {
        composable(route = Screen.HomeRoute.route){
            HomeRoute(
                onEditClick = { navController.navigate(Screen.EditRoute.route) }
            )
        }
        composable(route = Screen.DetailsScreen.route){
            DetailsRoute()
        }
        composable(route = Screen.EditRoute.route){
            EditRoute(
                onBackClick = { navController.popBackStack() }
            )
        }
    }

}