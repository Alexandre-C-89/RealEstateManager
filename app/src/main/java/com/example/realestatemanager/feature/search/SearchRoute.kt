package com.example.realestatemanager.feature.search

import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation.NavigableListDetailPaneScaffold
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.LightBlue
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.button.AppButton
import com.example.realestatemanager.designsystem.card.CardWithInfo
import com.example.realestatemanager.designsystem.card.ErrorCard
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Small
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.Spacings

@Composable
fun SearchRoute(
    isExpandedScreen: Boolean,
    viewModel: SearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiData by viewModel.data.collectAsStateWithLifecycle()
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    SearchScreen(
        isExpandedScreen = isExpandedScreen,
        data = uiData,
        searchState = searchState,
        onPriceMinChanged = { viewModel.onPriceMinChanged(it) },
        onPriceMaxChanged = { viewModel.onPriceMaxChanged(it) },
        onSurfaceMinChanged = { viewModel.onSurfaceMinChanged(it) },
        onSurfaceMaxChanged = { viewModel.onSurfaceMaxChanged(it) },
        onSchoolChanged = { viewModel.onSchoolChanged(it) },
        onShopsChanged = { viewModel.onShopsChanged(it) },
        onSaleChanged = { viewModel.onSaleChanged(it) },
        onMinPhotosChanged = { viewModel.onMinPhotosChanged(it) },
        onMaxPhotosChanged = { viewModel.onMaxPhotosChanged(it) },
        onBackClick = onBackClick,
        onSaveClick = { viewModel.searchProperties() },
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3AdaptiveApi::class)
@Composable
fun SearchScreen(
    isExpandedScreen: Boolean,
    data: SearchUiData,
    searchState: SearchState,
    onPriceMinChanged: (TextFieldValue) -> Unit,
    onPriceMaxChanged: (TextFieldValue) -> Unit,
    onSurfaceMinChanged: (TextFieldValue) -> Unit,
    onSurfaceMaxChanged: (TextFieldValue) -> Unit,
    onSchoolChanged: (Boolean) -> Unit,
    onShopsChanged: (Boolean) -> Unit,
    onSaleChanged: (Boolean) -> Unit,
    onMinPhotosChanged: (TextFieldValue) -> Unit,
    onMaxPhotosChanged: (TextFieldValue) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {
    val focusManager = LocalFocusManager.current
    var searchByNearbySchools by remember { mutableStateOf(false) }
    var searchByNearbyBusinesses by remember { mutableStateOf(false) }
    var forSale by remember { mutableStateOf(false) }
    AppScaffold(
        topBar = {
            TopBar(
                title = "Search property",
                onBackClick = onBackClick
            )
        }
    ) {

        if (!isExpandedScreen) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer.Vertical.Default()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 100.dp, min = 60.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text("Price min") },
                        value = data.priceMin,
                        onValueChange = onPriceMinChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        ),
                        shape = RoundedCornerShape(6.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightBlue,
                            focusedContainerColor = White
                        )
                    )
                    Spacer.Horizontal.Small()
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text("Price max") },
                        value = data.priceMax,
                        onValueChange = onPriceMaxChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        shape = RoundedCornerShape(6.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightBlue,
                            focusedContainerColor = White
                        )
                    )
                }
                Spacer.Vertical.Small()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 100.dp, min = 60.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text("Surface min.") },
                        value = data.surfaceMin,
                        onValueChange = onSurfaceMinChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        ),
                        shape = RoundedCornerShape(6.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightBlue,
                            focusedContainerColor = White
                        )
                    )
                    Spacer.Horizontal.Small()
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text("Surface max") },
                        value = data.surfaceMax,
                        onValueChange = onSurfaceMaxChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        shape = RoundedCornerShape(6.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightBlue,
                            focusedContainerColor = White
                        )
                    )
                }
                Spacer.Vertical.Default()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = searchByNearbySchools,
                        onCheckedChange = { isChecked ->
                            searchByNearbySchools = isChecked
                            onSchoolChanged(isChecked)
                        }
                    )
                    Text(text = "School")
                    Spacer.Horizontal.Large()
                    Checkbox(
                        checked = searchByNearbyBusinesses,
                        onCheckedChange = { isChecked ->
                            searchByNearbyBusinesses = isChecked
                            onShopsChanged(isChecked)
                        }
                    )
                    Text(text = "Shops")
                    Checkbox(
                        checked = forSale,
                        onCheckedChange = { isChecked ->
                            forSale = isChecked
                            onSaleChanged(isChecked)
                        }
                    )
                    Text(text = "For sale")
                }
                Spacer.Vertical.Small()
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 100.dp, min = 60.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text("Photos min.") },
                        value = data.minPhotos,
                        onValueChange = onMinPhotosChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        ),
                        shape = RoundedCornerShape(6.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightBlue,
                            focusedContainerColor = White
                        )
                    )
                    Spacer.Horizontal.Small()
                    OutlinedTextField(
                        modifier = Modifier.weight(1f),
                        label = { Text("Photos max") },
                        value = data.maxPhotos,
                        onValueChange = onMaxPhotosChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        shape = RoundedCornerShape(6.dp),
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            unfocusedContainerColor = LightBlue,
                            focusedContainerColor = White
                        )
                    )
                }
                Spacer.Vertical.Default()
                AppButton(
                    onClick = onSaveClick,
                    text = "Search"
                )

                Spacer.Vertical.Medium()

                when (searchState) {
                    is SearchState.Error -> {
                        ErrorCard(message = "No properties found with our criteria !")
                    }

                    SearchState.Loading -> {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Research field is empty !")
                        }
                    }

                    is SearchState.Success -> {
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentHeight(),
                            verticalArrangement = Arrangement.spacedBy(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            items(searchState.dataList) { property ->
                                val imageList =
                                    property.image?.removeSurrounding("[", "]")?.split(",")
                                        ?.map { it.trim() } ?: emptyList()
                                CardWithInfo(
                                    location = property.address ?: "",
                                    price = property.price.toString(),
                                    type = property.type ?: "",
                                    onClick = {},
                                    imageUri = imageList.firstOrNull()
                                )
                            }
                        }
                    }
                }

            }
        } else {
            val navigator = rememberListDetailPaneScaffoldNavigator<Any>()
            NavigableListDetailPaneScaffold(
                navigator = navigator,
                listPane = {
                    Column(
                        modifier = Modifier
                            .width(150.dp)
                            .fillMaxHeight()
                            .padding(Spacings.Small)
                            .verticalScroll(rememberScrollState()),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer.Vertical.Default()
                        FormTextField(
                            modifier = Modifier.width(200.dp),
                            label = { Text(text = "Price min.") },
                            value = data.priceMin,
                            onValueChange = onPriceMinChanged,
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Next)
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                        )
                        FormTextField(
                            modifier = Modifier.width(200.dp),
                            label = { Text(text = "Price max.") },
                            value = data.priceMax,
                            onValueChange = onPriceMaxChanged,
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Next)
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                        )
                        FormTextField(
                            modifier = Modifier.width(200.dp),
                            label = { Text(text = "Surface min.") },
                            value = data.surfaceMin,
                            onValueChange = onSurfaceMinChanged,
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Next)
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                        )
                        FormTextField(
                            modifier = Modifier.width(200.dp),
                            label = { Text(text = "Surface max.") },
                            value = data.surfaceMax,
                            onValueChange = onSurfaceMaxChanged,
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                        )
                        OutlinedTextField(
                            modifier = Modifier.width(200.dp),
                            label = { Text("Photos min.") },
                            value = data.minPhotos,
                            onValueChange = onMinPhotosChanged,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Next
                            ),
                            keyboardActions = KeyboardActions(
                                onNext = {
                                    focusManager.moveFocus(FocusDirection.Next)
                                }
                            ),
                            shape = RoundedCornerShape(6.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = LightBlue,
                                focusedContainerColor = White
                            )
                        )
                        OutlinedTextField(
                            modifier = Modifier.width(200.dp),
                            label = { Text("Photos max") },
                            value = data.maxPhotos,
                            onValueChange = onMaxPhotosChanged,
                            keyboardOptions = KeyboardOptions(
                                imeAction = ImeAction.Done
                            ),
                            keyboardActions = KeyboardActions(
                                onDone = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }
                            ),
                            shape = RoundedCornerShape(6.dp),
                            singleLine = true,
                            colors = OutlinedTextFieldDefaults.colors(
                                unfocusedContainerColor = LightBlue,
                                focusedContainerColor = White
                            )
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Checkbox(
                                checked = searchByNearbySchools,
                                onCheckedChange = { isChecked ->
                                    searchByNearbySchools = isChecked
                                    onSchoolChanged(isChecked)
                                }
                            )
                            Text(text = "School")
                            Spacer.Horizontal.Small()
                            Checkbox(
                                checked = searchByNearbyBusinesses,
                                onCheckedChange = { isChecked ->
                                    searchByNearbyBusinesses = isChecked
                                    onShopsChanged(isChecked)
                                }
                            )
                            Text(text = "Shops")
                            Spacer.Horizontal.Small()
                            Checkbox(
                                checked = forSale,
                                onCheckedChange = { isChecked ->
                                    forSale = isChecked
                                    onSaleChanged(isChecked)
                                }
                            )
                            Text(text = "For sale")
                        }
                        Spacer.Vertical.Small()
                        AppButton(onClick = onSaveClick, text = "Search")
                    }
                },
                detailPane = {
                    when (searchState) {
                        is SearchState.Error -> {
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center
                            ){
                                ErrorCard(message = "No properties found with our criteria !")
                            }
                        }

                        SearchState.Loading -> {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Text(text = "Research field is empty !")
                            }
                        }

                        is SearchState.Success -> {
                            LazyColumn(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .wrapContentHeight(),
                                verticalArrangement = Arrangement.spacedBy(8.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                items(searchState.dataList) { property ->
                                    val imageList =
                                        property.image?.removeSurrounding("[", "]")?.split(",")
                                            ?.map { it.trim() } ?: emptyList()
                                    CardWithInfo(
                                        location = property.address ?: "",
                                        price = property.price.toString(),
                                        type = property.type ?: "",
                                        onClick = {},
                                        imageUri = imageList.firstOrNull()
                                    )
                                }
                            }
                        }
                    }
                }
            )
        }

    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        isExpandedScreen = false,
        data = SearchUiData(shops = true, school = false, sale = false),
        searchState = SearchState.Loading,
        onPriceMinChanged = { TextFieldValue("") },
        onPriceMaxChanged = { TextFieldValue("") },
        onSurfaceMinChanged = { TextFieldValue("") },
        onSurfaceMaxChanged = { TextFieldValue("") },
        onMinPhotosChanged = { TextFieldValue("") },
        onMaxPhotosChanged = { TextFieldValue("") },
        onSchoolChanged = { true },
        onShopsChanged = { true },
        onSaleChanged = { true },
        onBackClick = {},
        onSaveClick = {}
    )
}