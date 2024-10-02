package com.example.realestatemanager.feature.modify

import android.annotation.SuppressLint
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
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
import com.example.realestatemanager.feature.modify.model.ModifyUiData

@Composable
fun ModifyRoute(
    isExpandedScreen: Boolean,
    viewModel: ModifyViewModel = hiltViewModel(),
    propertyId: Int,
    onBackClick: () -> Unit
) {
    val data by viewModel.data.collectAsStateWithLifecycle()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.onImageChanged(it.toString())
        }
    }

    LaunchedEffect(propertyId) {
        viewModel.loadProperty(propertyId)
    }

    ModifyScreen(
        isExpandedScreen = isExpandedScreen,
        onBackClick = onBackClick,
        onSaveClick = { viewModel.modifyProperty(propertyId, onBackClick) },
        data = data,
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

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModifyScreen(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    data: ModifyUiData,
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
        if (!isExpandedScreen){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Type") },
                    value = data.type,
                    onValueChange = { newValue -> onTypeChanged(newValue) },
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
                    modifier = Modifier.width(150.dp),
                    label = { Text("Price") },
                    value = data.price,
                    onValueChange = { newValue -> onPriceChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Surface") },
                    value = data.surface,
                    onValueChange = { newValue -> onSurfaceChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Horizontal.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Room") },
                    value = data.room,
                    onValueChange = { newValue -> onRoomChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Description") },
                    value = data.description,
                    onValueChange = { newValue -> onDescriptionChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Address") },
                    value = data.address,
                    onValueChange = { newValue -> onAddressChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Interest") },
                    value = data.interest,
                    onValueChange = { newValue -> onInterestChanged(newValue) },
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
                    modifier = Modifier.width(150.dp),
                    label = { Text("Status") },
                    value = data.status,
                    onValueChange = { newValue -> onStatusChanged(newValue) },
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
                    modifier = Modifier.width(150.dp),
                    label = { Text("Date of creation") },
                    value = data.dateOfCreation,
                    onValueChange = { newValue -> onDateOfCreationChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Horizontal.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("date of sold") },
                    value = data.dateOfSold,
                    onValueChange = { newValue -> onDateOfSoldChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Agent") },
                    value = data.agent,
                    onValueChange = { newValue -> onAgentChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppButton(onClick = onPickImageClick, text = "Modify image")
                    Spacer.Horizontal.Default()
                    AppButton(onClick = onSaveClick, text = "Save")
                }
            }
        } else {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Type") },
                    value = data.type,
                    onValueChange = { newValue -> onTypeChanged(newValue) },
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
                    modifier = Modifier.width(150.dp),
                    label = { Text("Price") },
                    value = data.price,
                    onValueChange = { newValue -> onPriceChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Surface") },
                    value = data.surface,
                    onValueChange = { newValue -> onSurfaceChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Horizontal.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Room") },
                    value = data.room,
                    onValueChange = { newValue -> onRoomChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Description") },
                    value = data.description,
                    onValueChange = { newValue -> onDescriptionChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Address") },
                    value = data.address,
                    onValueChange = { newValue -> onAddressChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Interest") },
                    value = data.interest,
                    onValueChange = { newValue -> onInterestChanged(newValue) },
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
                    modifier = Modifier.width(150.dp),
                    label = { Text("Status") },
                    value = data.status,
                    onValueChange = { newValue -> onStatusChanged(newValue) },
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
                    modifier = Modifier.width(150.dp),
                    label = { Text("Date of creation") },
                    value = data.dateOfCreation,
                    onValueChange = { newValue -> onDateOfCreationChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Horizontal.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("date of sold") },
                    value = data.dateOfSold,
                    onValueChange = { newValue -> onDateOfSoldChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(150.dp),
                    label = { Text("Agent") },
                    value = data.agent,
                    onValueChange = { newValue -> onAgentChanged(newValue) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.moveFocus(FocusDirection.Down)
                        }
                    )
                )
                Spacer.Vertical.Default()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppButton(onClick = onPickImageClick, text = "Modify image")
                    Spacer.Horizontal.Default()
                    AppButton(onClick = onSaveClick, text = "Save")
                }
            }
        }
    }
}