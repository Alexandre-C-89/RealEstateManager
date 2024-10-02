package com.example.realestatemanager.feature

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.navigation.AppNavRail
import com.example.realestatemanager.navigation.AppNavigation
import com.example.realestatemanager.navigation.RealEstateManagerNavigationActions
import com.example.realestatemanager.navigation.Screen
import kotlinx.coroutines.launch

@Composable
fun RealEstateManagerApp(
    widthSizeClass: WindowWidthSizeClass,
){
    RealEstateManagerTheme {
        val navController = rememberNavController()
        val navigationActions = remember(navController) {
            RealEstateManagerNavigationActions(navController)
            //JetnewsNavigationActions(navController)
        }

        val coroutineScope = rememberCoroutineScope()

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute =
            navBackStackEntry?.destination?.route ?: Screen.HomeRoute.route//JetnewsDestinations.HOME_ROUTE

        val isExpandedScreen = widthSizeClass == WindowWidthSizeClass.Expanded
        val sizeAwareDrawerState = rememberSizeAwareDrawerState(isExpandedScreen)

        ModalNavigationDrawer(
            drawerContent = {
                AppDrawer(
                    drawerState = sizeAwareDrawerState,
                    currentRoute = currentRoute,
                    navigateToHome = navigationActions.navigateToHome,
                    navigateToEdit = navigationActions.navigateToEdit,
                    navigateToSearch = navigationActions.navigateToSearch,
                    navigateToMap = navigationActions.navigateToMap,
                    navigateToLend = navigationActions.navigateToLend,
                    closeDrawer = { coroutineScope.launch { sizeAwareDrawerState.close() } }
                )
            },
            drawerState = sizeAwareDrawerState,
            // Only enable opening the drawer via gestures if the screen is not expanded
            gesturesEnabled = !isExpandedScreen
        ) {
            Row {
                if (isExpandedScreen) {
                    AppNavRail(
                        currentRoute = currentRoute,
                        navigateToHome = navigationActions.navigateToHome,
                        navigateToEdit = navigationActions.navigateToEdit,
                        navigateToSearch = navigationActions.navigateToSearch,
                        navigateToMap = navigationActions.navigateToMap,
                        navigateToLend = navigationActions.navigateToLend,
                    )
                }
                AppNavigation(
                    isExpandedScreen = isExpandedScreen,
                    navController = navController,
                )
            }
        }
    }
}

@Composable
private fun rememberSizeAwareDrawerState(isExpandedScreen: Boolean): DrawerState {
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    return if (!isExpandedScreen) {
        // If we want to allow showing the drawer, we use a real, remembered drawer
        // state defined above
        drawerState
    } else {
        // If we don't want to allow the drawer to be shown, we provide a drawer state
        // that is locked closed. This is intentionally not remembered, because we
        // don't want to keep track of any changes and always keep it closed
        DrawerState(DrawerValue.Closed)
    }
}