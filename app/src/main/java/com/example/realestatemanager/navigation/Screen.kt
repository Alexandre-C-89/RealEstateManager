package com.example.realestatemanager.navigation

sealed class Screen(val route: String) {
    object HomeRoute: Screen(route = "home")
    object DetailsScreen: Screen(route = "details")
    object EditRoute: Screen(route = "edit")
}