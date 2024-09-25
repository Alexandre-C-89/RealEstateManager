package com.example.realestatemanager.feature.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.button.AppButton
import com.example.realestatemanager.designsystem.card.CardWithImage
import com.example.realestatemanager.designsystem.card.ErrorCard
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun SearchRoute(
    viewModel: SearchViewModel = hiltViewModel(),
    onBackClick: () -> Unit,
) {
    val uiData by viewModel.data.collectAsStateWithLifecycle()
    val searchState by viewModel.searchState.collectAsStateWithLifecycle()
    SearchScreen(
        data = uiData,
        searchState = searchState,
        onPriceMinChanged = { viewModel.onPriceMinChanged(it) },
        onPriceMaxChanged = { viewModel.onPriceMaxChanged(it) },
        onSurfaceMinChanged = { viewModel.onSurfaceMinChanged(it) },
        onSurfaceMaxChanged = { viewModel.onSurfaceMaxChanged(it) },
        onBackClick = onBackClick,
        onSaveClick = { viewModel.searchProperties() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchScreen(
    data: SearchUiData,
    searchState: SearchState,
    onPriceMinChanged: (TextFieldValue) -> Unit,
    onPriceMaxChanged: (TextFieldValue) -> Unit,
    onSurfaceMinChanged: (TextFieldValue) -> Unit,
    onSurfaceMaxChanged: (TextFieldValue) -> Unit,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
) {

    val focusManager = LocalFocusManager.current

    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        },
        bottomBar = { BottomBar() }
    ) {

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer.Vertical.Default()
            Text(
                text = "Search your property",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall
            )
            Spacer.Vertical.Default()
            FormTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                value = data.priceMin,
                title = "Price Min.",
                onValueChange = onPriceMinChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            FormTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                title = "Price Max.",
                value = data.priceMax,
                onValueChange = onPriceMaxChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            FormTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                title = "Surface Min.",
                value = data.surfaceMin,
                onValueChange = onSurfaceMinChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            FormTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                title = "Surface Max.",
                value = data.surfaceMax,
                onValueChange = onSurfaceMaxChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            AppButton(
                onClick = onSaveClick,
                text = "Search"
            )

            Spacer.Vertical.Medium()

            when(searchState){
                is SearchState.Error -> {
                    ErrorCard(message = "Error with research !")
                }
                SearchState.Loading -> {
                    CircularProgressIndicator()
                }
                is SearchState.Success -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(8.dp)
                            .wrapContentHeight(),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(searchState.dataList) { property ->
                            CardWithImage(
                                location = property.address ?: "",
                                price = property.price.toString(),
                                type = property.type ?: "",
                                onClick = {},
                                imageUri = property.image
                            )
                        }
                    }
                }
            }

        }

    }
}

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        data = SearchUiData(),
        searchState = SearchState.Loading,
        onPriceMinChanged = { TextFieldValue("") },
        onPriceMaxChanged = { TextFieldValue("") },
        onSurfaceMinChanged = { TextFieldValue("") },
        onSurfaceMaxChanged = { TextFieldValue("") },
        onBackClick = {},
        onSaveClick = {},
    )
}