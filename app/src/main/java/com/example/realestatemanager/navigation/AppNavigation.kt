package com.example.realestatemanager.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
        composable(route = Screen.HomeRoute.route) {
            HomeRoute(
                onEditClick = { navController.navigate(Screen.EditRoute.route) },
                onPropertyClick = { propertyId ->
                    Log.d("AppNavigation", "Navigating to Details with ID: $propertyId")
                    navController.navigate("details/$propertyId")
                }
            )
        }
        composable(
            route = Screen.DetailsScreen.route + "/{propertyId}",
            arguments = listOf(navArgument("propertyId") { type = NavType.IntType })
        ) { backStackEntry ->
            // Use getInt() to retrieve the parameter value
            val propertyId = backStackEntry.arguments?.getInt("propertyId")
            Log.d("AppNavigation", "Navigating to Details with ID: $propertyId")
            if (propertyId != null) {
                DetailsRoute(propertyId = propertyId)
            } else {
                Log.e("AppNavigation", "propertyId is null")
                // Handle the null case or show an error
                DetailsRoute(propertyId = 0) // Default value or navigate to an error screen
            }
        }
        composable(route = Screen.EditRoute.route) {
            EditRoute(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}