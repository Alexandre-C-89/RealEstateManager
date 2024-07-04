package com.example.realestatemanager.main

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar

@Composable
fun HomeRoute() {
    HomeScreen(
        onMenuClick = {},
        onAddClick = {},
        onEditClick = {},
        onSearchClick = {},
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    onMenuClick: () -> Unit,
    onAddClick: () -> Unit,
    onEditClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onNavigationClick = onMenuClick,
                onAddClick = onAddClick,
                onEditClick = onEditClick,
                onSearchClick = onSearchClick,
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "photo of appartment or house",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "title")
                        Text(text = "description")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "photo of appartment or house",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "title")
                        Text(text = "description")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "photo of appartment or house",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "title")
                        Text(text = "description")
                    }
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
            ) {
                Row(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = "photo of appartment or house",
                        contentScale = ContentScale.Crop
                    )
                    Column(
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "title")
                        Text(text = "description")
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun MainRoutePreview() {
    HomeScreen(
        onMenuClick = {},
        onAddClick = {},
        onEditClick = {},
        onSearchClick = {}
    )
}