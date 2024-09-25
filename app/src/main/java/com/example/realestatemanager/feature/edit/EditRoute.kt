package com.example.realestatemanager.feature.edit

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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

    val uiData by viewModel.data.collectAsStateWithLifecycle()
    // Utilisation de ActivityResultLauncher pour sélectionner une image
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.onImageChanged(it.toString()) // Utilise l'URI pour l'image sélectionnée
        }
    }

    EditScreen(
        onBackClick = onBackClick,
        onSaveClick = { viewModel.saveProperty(onBackClick) },
        data = uiData,
        onPickImageClick = { launcher.launch("image/*") },
        onTypeChanged = { viewModel.onTypeChanged(it) },
        onPriceChanged = { viewModel.onPriceChanged(it) },
        onSurfaceChanged = { viewModel.onSurfaceChanged(it) },
        onRoomChanged = { viewModel.onRoomChanged(it) },
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
    onPickImageClick: () -> Unit,
    onTypeChanged: (TextFieldValue) -> Unit,
    onPriceChanged: (TextFieldValue) -> Unit,
    onSurfaceChanged: (TextFieldValue) -> Unit,
    onRoomChanged: (TextFieldValue) -> Unit,
    onDescriptionChanged: (TextFieldValue) -> Unit,
    onAddressChanged: (TextFieldValue) -> Unit,
    onInterestChanged: (TextFieldValue) -> Unit,
    onStatusChanged: (TextFieldValue) -> Unit,
    onDateOfCreationChanged: (TextFieldValue) -> Unit,
    onDateOfSoldChanged: (TextFieldValue) -> Unit,
    onAgentChanged: (TextFieldValue) -> Unit
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
                        text = "Create new property",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.displaySmall
                    )

                    Spacer.Vertical.Small()

                    FormTextField(
                        label = { Text("Type") },
                        value = data.type,
                        onValueChange = onTypeChanged
                    )
                    FormTextField(
                        label = { Text("Price") },
                        value = data.price,
                        onValueChange = onPriceChanged
                    )
                    FormTextField(
                        label = { Text("Surface") },
                        value = data.surface,
                        onValueChange = onSurfaceChanged
                    )
                    FormTextField(
                        label = { Text("Room") },
                        value = data.room,
                        onValueChange = onRoomChanged
                    )
                    AppButton(
                        onClick = onPickImageClick,
                        text = "Select image"
                    )
                    FormTextField(
                        label = { Text("Description") },
                        value = data.description,
                        onValueChange = onDescriptionChanged
                    )
                    FormTextField(
                        label = { Text("Address") },
                        value = data.address,
                        onValueChange = onAddressChanged
                    )
                    FormTextField(
                        label = { Text("Interest") },
                        value = data.interest,
                        onValueChange = onInterestChanged
                    )
                    FormTextField(
                        label = { Text("Status") },
                        value = data.status,
                        onValueChange = onStatusChanged
                    )
                    FormTextField(
                        label = { Text("Date of creation") },
                        value = data.dateOfCreation,
                        onValueChange = onDateOfCreationChanged
                    )
                    FormTextField(
                        label = { Text("Date of sold") },
                        value = data.dateOfSold,
                        onValueChange = onDateOfSoldChanged
                    )
                    FormTextField(
                        label = { Text("Agent") },
                        value = data.agent,
                        onValueChange = onAgentChanged
                    )

                    Spacer.Vertical.Default()

                    AppButton(
                        onClick = onSaveClick,
                        text = "Save"
                    )
                }
            }
        }
    }
}