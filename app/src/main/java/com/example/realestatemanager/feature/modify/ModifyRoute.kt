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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
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
import com.example.realestatemanager.feature.modify.model.ModifyUiData

@Composable
fun ModifyRoute(
    isExpandedScreen: Boolean,
    viewModel: ModifyViewModel = hiltViewModel(),
    propertyId: Int,
    onBackClick: () -> Unit
) {
    val context = LocalContext.current
    val data by viewModel.data.collectAsStateWithLifecycle()

    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            viewModel.currentPhotoUri?.let { uri ->
                viewModel.onImageChanged(uri.toString())
            }
        }
    }

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
        onCameraClick = {
            viewModel.takePhoto(context) { uri ->
                uri?.let { cameraLauncher.launch(it) }
            }
        },
        onTypeChanged = { viewModel.onTypeChanged(it) },
        onPriceChanged = { viewModel.onPriceChanged(it) },
        onSurfaceChanged = { viewModel.onSurfaceChanged(it) },
        onRoomChanged = { viewModel.onRoomChanged(it) },
        onDescriptionChanged = { viewModel.onDescriptionChanged(it) },
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
    onCameraClick: () -> Unit,
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
                title = "Modify",
                onBackClick = onBackClick
            )
        }
    ) {
        if (!isExpandedScreen){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FormTextField(
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
                    modifier = Modifier.fillMaxWidth(),
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
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer.Vertical.Default()
                Text(
                    text = "Modify this property",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displaySmall
                )
                Spacer.Vertical.ExtraLarge()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                }
                Spacer.Vertical.Default()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Spacer.Vertical.Default()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
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
                }
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier.width(200.dp),
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
                Spacer.Vertical.Large()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    AppButton(
                        onClick = onPickImageClick,
                        text = "Select image from galery"
                    )
                    Spacer.Horizontal.Default()
                    AppButton(
                        onClick = onCameraClick,
                        text = "Take a Photo"
                    )
                    Spacer.Horizontal.Small()
                    AppButton(onClick = onSaveClick, text = "Save")
                }
            }
        }
    }
}