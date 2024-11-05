package com.example.realestatemanager.feature.edit

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.LightBlue
import com.example.realestatemanager.designsystem.White
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.button.AppButton
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.domain.repository.PropertyRepository
import kotlinx.coroutines.launch

@Composable
fun EditRoute(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit,
    viewModel: EditViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val uiData by viewModel.data.collectAsStateWithLifecycle()
    val cameraLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture()
    ) { success: Boolean ->
        if (success) {
            viewModel.currentTakePhotoUri?.let { uri ->
                viewModel.onImageChanged(listOf(uri.toString()))
            }
        }
    }

    val galleryLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.OpenMultipleDocuments()) {
            it?.let { viewModel.chooseImageFromGallery(it) }
        }

    EditScreen(
        isExpandedScreen = isExpandedScreen,
        onBackClick = onBackClick,
        onSaveClick = { viewModel.saveProperty(onBackClick) },
        viewModel = viewModel,
        data = uiData,
        onPickImageClick = { galleryLauncher.launch(arrayOf("image/*")) },
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
        onSchoolChanged = { viewModel.onSchoolChanged(it) },
        onShopsChanged = { viewModel.onShopsChanged(it) },
        onSaleChanged = { viewModel.onSaleChanged(it) },
        onDateOfCreationChanged = { viewModel.onDateOfCreationChanged(it) },
        onDateOfSoldChanged = { viewModel.onDateOfSoldChanged(it) },
        onAgentChanged = { viewModel.onAgentChanged(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    isExpandedScreen: Boolean,
    onBackClick: () -> Unit,
    onSaveClick: () -> Unit,
    viewModel: EditViewModel,
    data: EditUiData,
    onPickImageClick: () -> Unit,
    onCameraClick: () -> Unit,
    onTypeChanged: (TextFieldValue) -> Unit,
    onPriceChanged: (TextFieldValue) -> Unit,
    onSurfaceChanged: (TextFieldValue) -> Unit,
    onRoomChanged: (TextFieldValue) -> Unit,
    onDescriptionChanged: (TextFieldValue) -> Unit,
    onAddressChanged: (TextFieldValue) -> Unit,
    onSchoolChanged: (Boolean) -> Unit,
    onShopsChanged: (Boolean) -> Unit,
    onSaleChanged: (Boolean) -> Unit,
    onDateOfCreationChanged: (TextFieldValue) -> Unit,
    onDateOfSoldChanged: (TextFieldValue) -> Unit,
    onAgentChanged: (TextFieldValue) -> Unit
) {

    val focusManager = LocalFocusManager.current
    var searchByNearbySchools by remember { mutableStateOf(false) }
    var searchByNearbyBusinesses by remember { mutableStateOf(false) }
    var forSale by remember { mutableStateOf(false) }
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()
    val keyboardHeight = WindowInsets.ime.getBottom(LocalDensity.current)
    LaunchedEffect(key1 = keyboardHeight) {
        coroutineScope.launch {
            scrollState.scrollBy(keyboardHeight.toFloat())
        }
    }

    AppScaffold(
        topBar = {
            TopBar(
                title = "Create property",
                onBackClick = onBackClick
            )
        }
    ) {
        if (!isExpandedScreen) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .imePadding()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer.Vertical.Small()
                Column(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    FormTextField(
                        modifier = Modifier,
                        label = { Text("Type") },
                        value = data.type,
                        onValueChange = onTypeChanged,
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
                        modifier = Modifier,
                        label = { Text("Price") },
                        value = data.price,
                        onValueChange = onPriceChanged,
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
                        modifier = Modifier,
                        label = { Text("Surface") },
                        value = data.surface,
                        onValueChange = onSurfaceChanged,
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
                        modifier = Modifier,
                        label = { Text("Room") },
                        value = data.room,
                        onValueChange = onRoomChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
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
                    }
                    FormTextField(
                        modifier = Modifier,
                        label = { Text("Description") },
                        value = data.description,
                        onValueChange = onDescriptionChanged,
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
                        modifier = Modifier,
                        label = { Text("Address") },
                        value = data.address,
                        onValueChange = onAddressChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        )
                    )
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
                        Text(text = "school")
                        Spacer.Horizontal.Large()
                        Checkbox(
                            checked = searchByNearbyBusinesses,
                            onCheckedChange = { isChecked ->
                                searchByNearbyBusinesses = isChecked
                                onShopsChanged(isChecked)
                            }
                        )
                        Text(text = "shops")
                        Checkbox(
                            checked = forSale,
                            onCheckedChange = { isChecked ->
                                forSale = isChecked
                                onSaleChanged(isChecked)
                            }
                        )
                        Text(text = "For sale")
                    }
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        label = { Text("Date of creation") },
                        value = data.dateOfCreation,
                        onValueChange = { newValue -> onDateOfCreationChanged(newValue) },
                        isError = !viewModel.isDateValid,
                        supportingText = {
                            if (!viewModel.isDateValid) Text(viewModel.dateError, color = Color.Red)
                        },
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
                            focusedContainerColor = White,
                            errorBorderColor = Color.Red
                        )
                    )
                    FormTextField(
                        modifier = Modifier,
                        label = { Text("Date of sold") },
                        value = data.dateOfSold,
                        onValueChange = onDateOfSoldChanged,
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
                        modifier = Modifier,
                        label = { Text("Agent") },
                        value = data.agent,
                        onValueChange = onAgentChanged,
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

                    AppButton(
                        onClick = onSaveClick,
                        text = "Save",
                        enabled = viewModel.isDateValid
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
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
                        onValueChange = onTypeChanged,
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
                        onValueChange = onPriceChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
                        label = { Text("Surface") },
                        value = data.surface,
                        onValueChange = onSurfaceChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
                        label = { Text("Room") },
                        value = data.room,
                        onValueChange = onRoomChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
                        label = { Text("Description") },
                        value = data.description,
                        onValueChange = onDescriptionChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
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
                    FormTextField(
                        modifier = Modifier.width(200.dp),
                        label = { Text("Address") },
                        value = data.address,
                        onValueChange = onAddressChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
                        label = { Text("Date of creation") },
                        value = data.dateOfCreation,
                        onValueChange = onDateOfCreationChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier.width(200.dp),
                        label = { Text("Date of sold") },
                        value = data.dateOfSold,
                        onValueChange = onDateOfSoldChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    Spacer.Horizontal.Small()
                    FormTextField(
                        modifier = Modifier,
                        label = { Text("Agent") },
                        value = data.agent,
                        onValueChange = onAgentChanged,
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