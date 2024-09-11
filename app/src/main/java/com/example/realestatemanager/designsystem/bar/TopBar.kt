package com.example.realestatemanager.designsystem.bar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.designsystem.DarkBlue
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.button.AddIconButton
import com.example.realestatemanager.designsystem.button.BackIconButton
import com.example.realestatemanager.designsystem.button.EditIconButton
import com.example.realestatemanager.designsystem.button.MapIconButton
import com.example.realestatemanager.designsystem.button.MenuIconButton
import com.example.realestatemanager.designsystem.button.SearchIconButton
import com.example.realestatemanager.designsystem.fonts

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    scrollBehavior: TopAppBarScrollBehavior? = null,
    onNavigationClick: (() -> Unit)? = null,
    //onAddClick: (() -> Unit)? = null,
    onMapClick: (() -> Unit)? = null,
    onEditClick: (() -> Unit)? = null,
    onSearchClick: (() -> Unit)? = null,
    onBackClick: (() -> Unit)? = null,
) {
    Surface {
        TopAppBar(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            title = {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "RealEstateManager",
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                            fontFamily = fonts,
                            textAlign = TextAlign.Start,
                            color = White
                        )
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = DarkBlue,
                titleContentColor = White,
                actionIconContentColor = White,
                navigationIconContentColor = White
            ),
            navigationIcon = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onNavigationClick?.let {
                        MenuIconButton { onNavigationClick() }
                    }
                }
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onBackClick?.let {
                        BackIconButton { onBackClick() }
                    }
                }
            },
            actions = {
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onMapClick?.let {
                        MapIconButton { onMapClick() }
                    }
                }
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onEditClick?.let {
                        EditIconButton { onEditClick() }
                    }
                }
                Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                    onSearchClick?.let {
                        SearchIconButton { onSearchClick() }
                    }
                }
            },
            scrollBehavior = scrollBehavior
        )
    }
}