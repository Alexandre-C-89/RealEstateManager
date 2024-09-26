package com.example.realestatemanager.feature.search

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.LightBlue
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.button.AppButton
import com.example.realestatemanager.designsystem.card.CardWithInfo
import com.example.realestatemanager.designsystem.card.ErrorCard
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Spacer

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
        onSaveClick = {
            Log.d("ONSAVECLICK","I click on Search button")
            viewModel.searchProperties()
        },
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
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Spacer.Vertical.Default()
            Text(
                text = "Search your property",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.displaySmall,
                textAlign = TextAlign.Center
            )
            Spacer.Vertical.Default()
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(max = 100.dp, min = 60.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
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
            ){
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
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(searchState.dataList) { property ->
                            CardWithInfo(
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