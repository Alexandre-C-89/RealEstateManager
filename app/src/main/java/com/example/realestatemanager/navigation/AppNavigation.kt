package com.example.realestatemanager.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.realestatemanager.HomeViewModelFactory
import com.example.realestatemanager.database.Database
import com.example.realestatemanager.database.Repository
import com.example.realestatemanager.details.DetailsRoute
import com.example.realestatemanager.main.HomeRoute
import com.example.realestatemanager.main.HomeViewModel

@Composable
fun AppNavigation() {

    val navController = rememberNavController()
    val context = LocalContext.current
    val database = Database.getDatabase(context)
    val repository = Repository(database.propertyDao())
    val homeViewModel: HomeViewModel = viewModel(factory = HomeViewModelFactory(repository))

    NavHost(
        navController = navController,
        startDestination = Screen.HomeScreen.route
    ) {
        composable(route = Screen.HomeScreen.route){
            HomeRoute(viewModel = homeViewModel)
        }
        composable(route = Screen.DetailsScreen.route){
            DetailsRoute()
        }
    }

}