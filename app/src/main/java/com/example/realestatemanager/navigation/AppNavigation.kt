package com.example.realestatemanager.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.realestatemanager.feature.details.DetailsRoute
import com.example.realestatemanager.feature.edit.EditRoute
import com.example.realestatemanager.feature.lend.LendRoute
import com.example.realestatemanager.feature.main.HomeRoute
import com.example.realestatemanager.feature.map.MapRoute
import com.example.realestatemanager.feature.modify.ModifyRoute
import com.example.realestatemanager.feature.search.SearchRoute

@Composable
fun AppNavigation(
    isExpandedScreen: Boolean,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.HomeRoute.route
    ) {
        composable(route = Screen.HomeRoute.route) {
            HomeRoute(
                isExpandedScreen = isExpandedScreen,
                onEditClick = { navController.navigate(Screen.EditRoute.route) },
                onHomeClick = { navController.navigate(Screen.HomeRoute.route) },
                onPropertyClick = { propertyId ->
                    navController.navigate("details/$propertyId")
                },
                onMapClick = { navController.navigate(Screen.MapRoute.route) },
                onSearchClick = { navController.navigate(Screen.SearchRoute.route) },
                onLendClick = { navController.navigate(Screen.LendRoute.route) },
                onModifyClick = { propertyId ->
                    navController.navigate("modify/$propertyId")
                }
            )
        }
        composable(
            route = Screen.DetailsScreen.route + "/{propertyId}",
            arguments = listOf(navArgument("propertyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getInt("propertyId")
            if (propertyId != null) {
                DetailsRoute(
                    isExpandedScreen = isExpandedScreen,
                    propertyId = propertyId,
                    onBackClick = { navController.popBackStack() },
                    onModifyClick = { propertyId ->
                        navController.navigate("modify/$propertyId")
                    }
                )
            } else {
                DetailsRoute(
                    isExpandedScreen = isExpandedScreen,
                    propertyId = 0,
                    onBackClick = { navController.popBackStack() },
                    onModifyClick = { navController.navigate(Screen.ModifyRoute.route) }
                )
            }
        }
        composable(route = Screen.EditRoute.route) {
            EditRoute(
                isExpandedScreen = isExpandedScreen,
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
                isExpandedScreen = isExpandedScreen,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(route = Screen.LendRoute.route) {
            LendRoute(
                isExpandedScreen = isExpandedScreen,
                onBackClick = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.ModifyRoute.route + "/{propertyId}",
            arguments = listOf(navArgument("propertyId") { type = NavType.IntType })
        ) { backStackEntry ->
            val propertyId = backStackEntry.arguments?.getInt("propertyId")
            if (propertyId != null) {
                ModifyRoute(
                    isExpandedScreen = isExpandedScreen,
                    propertyId = propertyId,
                    onBackClick = { navController.popBackStack() },
                )
            } else {
                ModifyRoute(
                    isExpandedScreen = isExpandedScreen,
                    propertyId = 0,
                    onBackClick = { navController.popBackStack() }
                )
            }
        }
    }
}