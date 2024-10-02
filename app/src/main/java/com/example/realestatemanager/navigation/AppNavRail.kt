package com.example.realestatemanager.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.RealEstateManagerTheme

@Composable
fun AppNavRail(
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToEdit: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToLend: () -> Unit,
    modifier: Modifier = Modifier
){
    NavigationRail(
        header = {
            Icon(
                painterResource(R.drawable.ic_launcher_foreground),
                null,
                Modifier.padding(vertical = 12.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        },
        modifier = modifier
    ) {
        Spacer(Modifier.weight(1f))
        NavigationRailItem(
            selected = currentRoute == Screen.HomeRoute.route,
            onClick = navigateToHome,
            icon = { Icon(Icons.Filled.Home, "Home") },
            label = { Text("Home") },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == Screen.EditRoute.route,
            onClick = navigateToEdit,
            icon = { Icon(painterResource(id = R.drawable.ic_add), "Edit") },
            label = { Text("Edit") },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == Screen.SearchRoute.route,
            onClick = navigateToSearch,
            icon = { Icon(painterResource(id = R.drawable.ic_search), "Search") },
            label = { Text("Search") },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == Screen.MapRoute.route,
            onClick = navigateToMap,
            icon = { Icon(painterResource(id = R.drawable.ic_map), "Map") },
            label = { Text("Map") },
            alwaysShowLabel = false
        )
        NavigationRailItem(
            selected = currentRoute == Screen.LendRoute.route,
            onClick = navigateToLend,
            icon = { Icon(painterResource(id = R.drawable.ic_lend), "Lend") },
            label = { Text("Lend") },
            alwaysShowLabel = false
        )
        Spacer(Modifier.weight(1f))
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppNavRail() {
    RealEstateManagerTheme {
        AppNavRail(
            currentRoute = Screen.HomeRoute.route,
            navigateToHome = {},
            navigateToEdit = {},
            navigateToSearch = {},
            navigateToMap = {},
            navigateToLend = {},
        )
    }
}