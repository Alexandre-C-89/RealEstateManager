package com.example.realestatemanager.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen(route = "home")
    object DetailsScreen: Screen(route = "details")
}