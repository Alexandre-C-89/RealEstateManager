package com.example.realestatemanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.realestatemanager.HomeViewModelFactory
import com.example.realestatemanager.database.datasource.PropertyDatabase
import com.example.realestatemanager.database.repository.PropertyRepository
import com.example.realestatemanager.details.DetailsRoute
import com.example.realestatemanager.edit.EditRoute
import com.example.realestatemanager.main.HomeRoute
import com.example.realestatemanager.main.HomeViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val propertyDatabase = PropertyDatabase.getDatabase(context)
    val propertyRepository = PropertyRepository(propertyDatabase.propertyDao())
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(propertyRepository, propertyDatabase = propertyDatabase))

    NavHost(
        navController = navController,
        startDestination = Screen.HomeRoute.route
    ) {
        composable(route = Screen.HomeRoute.route){
            HomeRoute(
                viewModel = homeViewModel,
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