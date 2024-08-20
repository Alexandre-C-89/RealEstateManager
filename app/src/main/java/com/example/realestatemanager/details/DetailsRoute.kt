package com.example.realestatemanager.details

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.realestatemanager.R
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.Black
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.ListWithColumn
import com.example.realestatemanager.designsystem.LocationListItem
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.card.CardWithIcon
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.map.GoogleMapItem
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings
import com.example.realestatemanager.designsystem.ui.text.Text
import com.example.realestatemanager.main.HomeViewModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.rememberCameraPositionState

@Composable
fun DetailsRoute(
    onBackClick: () -> Unit,
    propertyId: Int,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val property by viewModel.getPropertyById(propertyId).collectAsState(initial = null)

    if (property != null) {
        DetailsScreen(
            property = property!!,
            onBackClick = onBackClick
        )
    } else {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun DetailsScreen(
    property: Property,
    onBackClick: () -> Unit,
) {
    val images = listOf(
        R.drawable.lounge,
        R.drawable.tv_lounge,
        R.drawable.dining_room
    )
    val pagerState = rememberPagerState(pageCount = { images.size })
    val leBourget = LatLng(48.936752, 2.425377)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(leBourget, .5f)
    }
    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(Spacings.Default)
            ) {
                Text(
                    text = "Description",
                    style = TextStyle(
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Blue
                    )
                )
                Spacer.Vertical.Small()
                Text(
                    text = property.description,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Black
                    )
                )
                Spacer.Vertical.Large()
                Text(
                    text = "Surface - ${property.surface} • piece • Room - ${property.room}",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Blue
                    )
                )
                Spacer.Vertical.Large()
                Text(text = "Location")
                Spacer.Vertical.Small()
                Text(
                    text = property.address,
                    style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Normal,
                        fontFamily = fonts,
                        textAlign = TextAlign.Start,
                        color = Blue
                    )
                )
            }
            Spacer.Vertical.Large()
            GoogleMapItem(
                cameraPositionState = cameraPositionState
            ) {

            }
        }
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    RealEstateManagerTheme {
        DetailsScreen(
            property = Property(
                id = 1,
                type = "House",
                price = 500000,
                surface = 200,
                room = 5,
                image = "sample_image",
                description = "A beautiful house in the city center.",
                address = "123 Main Street",
                interest = "Park",
                status = "For Sale",
                dateOfCreation = 1625241600000L,
                dateOfSold = null,
                agent = "John Doe"
            ),
            onBackClick = {}
        )
    }
}