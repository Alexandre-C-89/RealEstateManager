package com.example.realestatemanager.feature

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.navigation.Screen

@Composable
fun AppDrawer(
    drawerState: DrawerState,
    currentRoute: String,
    navigateToHome: () -> Unit,
    navigateToEdit: () -> Unit,
    navigateToSearch: () -> Unit,
    navigateToMap: () -> Unit,
    navigateToLend: () -> Unit,
    closeDrawer: () -> Unit,
    modifier: Modifier = Modifier
){
    ModalDrawerSheet(
        drawerState = drawerState,
        modifier = modifier,
    ) {
        JetNewsLogo(
            modifier = Modifier.padding(horizontal = 28.dp, vertical = 24.dp)
        )
        NavigationDrawerItem(
            label = { Text("Home") },
            icon = { Icon(Icons.Filled.Home, null) },
            selected = currentRoute == Screen.HomeRoute.route,
            onClick = { navigateToHome(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Edit") },
            icon = { Icon(Icons.Default.Add, null) },
            selected = currentRoute == Screen.EditRoute.route,
            onClick = { navigateToEdit(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Search") },
            icon = { Icon(Icons.Default.Search, null) },
            selected = currentRoute == Screen.SearchRoute.route,
            onClick = { navigateToSearch(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Map") },
            icon = { painterResource(R.drawable.ic_map) },
            selected = currentRoute == Screen.MapRoute.route,
            onClick = { navigateToMap(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
        NavigationDrawerItem(
            label = { Text("Lend") },
            icon = { painterResource(R.drawable.ic_lend) },
            selected = currentRoute == Screen.LendRoute.route,
            onClick = { navigateToLend(); closeDrawer() },
            modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
        )
    }
}

@Composable
private fun JetNewsLogo(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Icon(
            painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview("Drawer contents")
@Preview("Drawer contents (dark)", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun PreviewAppDrawer() {
    RealEstateManagerTheme {
        AppDrawer(
            drawerState = rememberDrawerState(initialValue = DrawerValue.Open),
            currentRoute = Screen.HomeRoute.route,
            navigateToHome = {},
            navigateToEdit = {},
            navigateToSearch = {},
            navigateToMap = {},
            navigateToLend = {},
            closeDrawer = { }
        )
    }
}