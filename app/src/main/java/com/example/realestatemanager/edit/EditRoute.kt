package com.example.realestatemanager.edit

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.TopBar

@Composable
fun EditRoute(
    onBackClick: () -> Unit,
    //viewModel: EditViewModel = viewModel(),
) {
    //val uiData: Property by viewModel.uiData.collectAsStateWithLifecycle()
    EditScreen(
        onBackClick = onBackClick,
        //data = uiData,
        /*onTypeChanged = { viewModel.onTypeChanged(it) },
        onPriceChanged = { viewModel.onPriceChanged(it) },
        onSurfaceChanged = { viewModel.onSurfaceChanged(it) },
        onRoomChanged = { viewModel.onRoomChanged(it) },
        onDescritpionChanged = { viewModel.onDescritpionChanged(it) },
        onAddressChanged = { viewModel.onAddressChanged(it) },
        onInterestChanged = { viewModel.onInterestChanged(it) },
        onStatusChanged = { viewModel.onStatusChanged(it) },
        onDateOfCreationChanged = { viewModel.onDateOfCreationChanged(it) },
        onDateOfSoldChanged = { viewModel.onDateOfSoldChanged(it) },
        onAgentChanged = { viewModel.onAgentChanged(it) },*/
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditScreen(
    onBackClick: () -> Unit,
    //data: Property,
    /*onTypeChanged: (TextFieldValue) -> Unit,
    onPriceChanged: (TextFieldValue) -> Unit,
    onSurfaceChanged: (TextFieldValue) -> Unit,
    onRoomChanged: (TextFieldValue) -> Unit,
    onDescritpionChanged: (TextFieldValue) -> Unit,
    onAddressChanged: (TextFieldValue) -> Unit,
    onInterestChanged: (TextFieldValue) -> Unit,
    onStatusChanged: (TextFieldValue) -> Unit,
    onDateOfCreationChanged: (TextFieldValue) -> Unit,
    onDateOfSoldChanged: (TextFieldValue) -> Unit,
    onAgentChanged: (TextFieldValue) -> Unit*/
) {
    AppScaffold(
        topBar = { TopBar(
            onNavigationClick = onBackClick
        ) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            /*TextField(value = data.type, onValueChange = onTypeChanged, label = { Text("Type") })
            TextField(value = data.price.toString(), onValueChange = onPriceChanged, label = { Text(text = "Price") })
            TextField(value = data.price.toString(), onValueChange = onSurfaceChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onRoomChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onDescritpionChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onAddressChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onInterestChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onStatusChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onDateOfCreationChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onDateOfSoldChanged, label = { Text("Price") })
            TextField(value = data.price.toString(), onValueChange = onAgentChanged, label = { Text("Price") })*/
        }
    }
}