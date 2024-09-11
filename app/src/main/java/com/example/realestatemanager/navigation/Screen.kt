package com.example.realestatemanager.navigation

sealed class Screen(val route: String) {
    object HomeRoute: Screen(route = "Home")
    object DetailsScreen: Screen(route = "Details")
    object EditRoute: Screen(route = "Edit")
    object MapRoute: Screen(route = "Map")
}