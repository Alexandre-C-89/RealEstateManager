package com.example.realestatemanager.feature.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Spacer

@Composable
fun EditRoute(
    onBackClick: () -> Unit,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val uiData by viewModel.data.collectAsStateWithLifecycle()
    EditScreen(
        onBackClick = onBackClick,
        onSaveClick = { viewModel.saveProperty(onBackClick) },
        data = uiData,
        onTypeChanged = { viewModel.onTypeChanged(it) },
        onPriceChanged = { viewModel.onPriceChanged(it) },
        onSurfaceChanged = { viewModel.onSurfaceChanged(it) },
        onRoomChanged = { viewModel.onRoomChanged(it) },
        onImageChanged = { viewModel.onImageChanged(it) },
        onDescriptionChanged = { viewModel.onDescritpionChanged(it) },
        onAddressChanged = { viewModel.onAddressChanged(it) },
        onInterestChanged = { viewModel.onInterestChanged(it) },
        onStatusChanged = { viewModel.onStatusChanged(it) },
        onDateOfCreationChanged = { viewModel.onDateOfCreationChanged(it) },
        onDateOfSoldChanged = { viewModel.onDateOfSoldChanged(it) },
        onAgentChanged = { viewModel.onAgentChanged(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    data: EditUiData,
    onTypeChanged: (TextFieldValue) -> Unit,
    onPriceChanged: (TextFieldValue) -> Unit,
    onSurfaceChanged: (TextFieldValue) -> Unit,
    onRoomChanged: (TextFieldValue) -> Unit,
    onImageChanged: (TextFieldValue) -> Unit,
    onDescriptionChanged: (TextFieldValue) -> Unit,
    onAddressChanged: (TextFieldValue) -> Unit,
    onInterestChanged: (TextFieldValue) -> Unit,
    onStatusChanged: (TextFieldValue) -> Unit,
    onDateOfCreationChanged: (TextFieldValue) -> Unit,
    onDateOfSoldChanged: (TextFieldValue) -> Unit,
    onAgentChanged: (TextFieldValue) -> Unit
) {
    AppScaffold(
        topBar = {
            TopBar(
                onBackClick = onBackClick
            )
        }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppScaffold {
                Column(
                    modifier = Modifier
                        .padding(16.dp, 16.dp)
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {

                    Text(
                        text = "type",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displaySmall
                    )

                    Spacer.Vertical.Small()

                    FormTextField(
                        textValue = data.type,
                        hint = "Type",
                        label = "Type",
                        onValueChange = onTypeChanged
                    )
                    FormTextField(
                        textValue = data.price,
                        hint = "Price",
                        label = "Price",
                        onValueChange = onPriceChanged
                    )
                    FormTextField(
                        textValue = data.surface,
                        hint = "Surface",
                        label = "Surface",
                        onValueChange = onSurfaceChanged
                    )
                    FormTextField(
                        textValue = data.room,
                        hint = "Room",
                        label = "Room",
                        onValueChange = onRoomChanged
                    )
                    FormTextField(
                        textValue = data.image,
                        hint = "Image",
                        label = "Image",
                        onValueChange = onImageChanged
                    )
                    FormTextField(
                        textValue = data.description,
                        hint = "Description",
                        label = "Description",
                        onValueChange = onDescriptionChanged
                    )
                    FormTextField(
                        textValue = data.address,
                        hint = "Address",
                        label = "Address",
                        onValueChange = onAddressChanged
                    )
                    FormTextField(
                        textValue = data.interest,
                        hint = "Interest",
                        label = "Interest",
                        onValueChange = onInterestChanged
                    )
                    FormTextField(
                        textValue = data.status,
                        hint = "Status",
                        label = "Status",
                        onValueChange = onStatusChanged
                    )
                    FormTextField(
                        textValue = data.dateOfCreation,
                        hint = "Date of creation",
                        label = "Date of creation",
                        onValueChange = onDateOfCreationChanged
                    )
                    FormTextField(
                        textValue = data.dateOfSold,
                        hint = "Date of Sold",
                        label = "Date of Sold",
                        onValueChange = onDateOfSoldChanged
                    )
                    FormTextField(
                        textValue = data.agent,
                        hint = "Agent",
                        label = "Agent",
                        onValueChange = onAgentChanged
                    )

                    Spacer.Vertical.Default()

                    Button(onClick = onSaveClick ) {
                        Text(text = "save")
                    }
                }
            }
        }
    }
}