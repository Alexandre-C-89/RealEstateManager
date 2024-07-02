package com.example.realestatemanager.details

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.R
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.bar.TopBar

@Composable
fun DetailsRoute(

) {
    DetailsScreen(

    )
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(

) {
    val images = listOf(
        R.drawable.lounge,
        R.drawable.tv_lounge,
        R.drawable.dining_room
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    Scaffold(
        topBar = { TopBar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Box(
                modifier = Modifier.wrapContentSize()
            ) {
                HorizontalPager(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(26.dp),
                    state = pagerState
                ) {currentPage ->
                    Card(
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(painter = painterResource(id = images[currentPage]), contentDescription = "image resource")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    RealEstateManagerTheme {
        DetailsScreen()
    }
}