package com.example.realestatemanager.details

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.ListWithColumn
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithIcon
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.designsystem.ui.text.Text

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
    AppScaffold(
        topBar = { TopBar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacings.Default)
        ) {
            Box{
                HorizontalPager(state = pagerState) {currentPage ->
                    Card {
                        Image(painter = painterResource(id = images[currentPage]), contentDescription = "image resource")
                    }
                }
            }
            Spacer.Vertical.Large()
            Text.Medium(text = "Description")
            Spacer.Vertical.Tiny()
            Text.Default(text = "lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum lorem ipsum")
            Spacer.Vertical.Large()
            ListWithColumn()
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