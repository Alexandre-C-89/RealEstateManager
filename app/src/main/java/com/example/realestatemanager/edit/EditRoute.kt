package com.example.realestatemanager.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.R
import com.example.realestatemanager.database.datasource.Property
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.button.AppButton
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Spacer

@Composable
fun EditRoute(
    onBackClick: () -> Unit,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val uiData: EditUiData by viewModel.data.collectAsStateWithLifecycle()
    EditScreen(
        onBackClick = onBackClick,
        onSaveClick = {
            viewModel.saveProperty(
                property = Property(
                    id = null,
                    type = uiData.type.text,
                    price = uiData.price.text.toInt(),
                    surface = uiData.surface.text.toInt(),
                    room = uiData.room.text.toInt(),
                    image = uiData.room.text,
                    description = uiData.description.text,
                    address = uiData.address.text,
                    interest = uiData.interest.text,
                    status = uiData.status.text,
                    dateOfCreation = uiData.dateOfCreation.text.toLong(),
                    dateOfSold = uiData.dateOfSold.text.toLong(),
                    agent = uiData.agent.text
                )
            )
        },
        data = uiData,
        onTypeChanged = { viewModel.onTypeChanged(it) },
        onPriceChanged = { viewModel.onPriceChanged(it) },
        onSurfaceChanged = { viewModel.onSurfaceChanged(it) },
        onRoomChanged = { viewModel.onRoomChanged(it) },
        onDescritpionChanged = { viewModel.onDescritpionChanged(it) },
        onAddressChanged = { viewModel.onAddressChanged(it) },
        onInterestChanged = { viewModel.onInterestChanged(it) },
        onStatusChanged = { viewModel.onStatusChanged(it) },
        onDateOfCreationChanged = { viewModel.onDateOfCreationChanged(it) },
        onDateOfSoldChanged = { viewModel.onDateOfSoldChanged(it) },
        onAgentChanged = { viewModel.onAgentChanged(it) },
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
    onDescritpionChanged: (TextFieldValue) -> Unit,
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
                onNavigationClick = onBackClick
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
                        textValue = data.description,
                        hint = "Description",
                        label = "Description",
                        onValueChange = onDescritpionChanged
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

                    AppButton(
                        onClick = onSaveClick,
                        enabled =
                        if (data.type.text.length < 5 && data.price.text.length < 6 && data.surface.text.isEmpty() && data.room.text.isEmpty() && data.description.text.length < 10 && data.address.text.length < 7 && data.interest.text.length < 10 && data.status.text.length < 7 && data.dateOfCreation.text.length < 7 && data.dateOfSold.text.length < 7 && data.agent.text.length < 5) false else true,
                        text = "Save"
                    )
                }
            }
        }
    }
}