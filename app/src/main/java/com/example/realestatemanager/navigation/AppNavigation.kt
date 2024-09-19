package com.example.realestatemanager.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.realestatemanager.feature.details.DetailsRoute
import com.example.realestatemanager.feature.edit.EditRoute
import com.example.realestatemanager.feature.main.HomeRoute
import com.example.realestatemanager.feature.map.MapRoute
import com.example.realestatemanager.feature.search.SearchRoute

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
                onHomeClick = { navController.navigate(Screen.HomeRoute.route) },
                onPropertyClick = { propertyId ->
                    navController.navigate("details/$propertyId")
                },
                onMapClick = { navController.navigate(Screen.MapRoute.route) },
                onSearchClick = { navController.navigate(Screen.SearchRoute.route) }
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
                DetailsRoute(
                    propertyId = propertyId,
                    onBackClick = { navController.popBackStack() },
                )
            } else {
                Log.e("AppNavigation", "propertyId is null")
                // Handle the null case or show an error
                DetailsRoute(
                    propertyId = 0,
                    onBackClick = { navController.popBackStack() },
                ) // Default value or navigate to an error screen
            }
        }
        composable(route = Screen.EditRoute.route) {
            EditRoute(
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = Screen.MapRoute.route) {
            MapRoute(
                onBackClick = { navController.popBackStack() },
                onHomeClick = { navController.navigate(Screen.HomeRoute.route) },
                onMapClick = { navController.navigate(Screen.MapRoute.route) },
                onEditClick = { navController.navigate(Screen.EditRoute.route) }
            )
        }
        composable(route = Screen.SearchRoute.route) {
            SearchRoute(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}