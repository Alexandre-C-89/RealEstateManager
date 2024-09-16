package com.example.realestatemanager.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar

@Composable
fun SearchRoute(

){
    SearchScreen(

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(

){
    AppScaffold(
        topBar = { TopBar() },
        bottomBar = { BottomBar() }
    ) {
        Column {
            Text(
                text = "Search your property",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall
            )
        }
    }
}

@Preview
@Composable
fun SearchScreenPreview(){
    SearchScreen()
}