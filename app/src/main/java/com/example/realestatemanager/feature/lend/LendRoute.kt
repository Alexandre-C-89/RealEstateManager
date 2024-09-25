package com.example.realestatemanager.feature.lend

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.bar.BottomBar
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.button.AppButton
import com.example.realestatemanager.designsystem.textfield.FormTextField
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text
import com.example.realestatemanager.designsystem.ui.text.Title
import com.example.realestatemanager.feature.lend.model.LendUiData

@Composable
fun LendRoute(
    viewModel: LendViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiData by viewModel.data.collectAsStateWithLifecycle()

    LendScreen(
        data = uiData,
        onBackClick = onBackClick,
        onTotalLoanAmountChanged = { viewModel.onTotalLoanAmountChanged(it) },
        onContributionChanged = { viewModel.onContributionChanged(it) },
        onRateChanged = { viewModel.onRateChanged(it) },
        onDurationChanged = { viewModel.onDurationChanged(it) },
        onCalculateClick = { viewModel.calculateLoan() },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LendScreen(
    data: LendUiData,
    onBackClick: () -> Unit,
    onTotalLoanAmountChanged: (TextFieldValue) -> Unit,
    onContributionChanged: (TextFieldValue) -> Unit,
    onRateChanged: (TextFieldValue) -> Unit,
    onDurationChanged: (TextFieldValue) -> Unit,
    onCalculateClick: () -> Unit
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
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        ) {
            Title.Default(text = "Choose option for lend")
            Spacer.Vertical.Default()
            FormTextField(
                label = { Text("Total Loan Amount") },
                value = data.totalLoanAmount,
                onValueChange = onTotalLoanAmountChanged
            )
            FormTextField(
                label = { Text("Contribution") },
                value = data.contribution,
                onValueChange = onContributionChanged
            )
            Spacer.Horizontal.Default()
            FormTextField(
                label = { Text("Rate") },
                value = data.rate,
                onValueChange = onRateChanged
            )
            Spacer.Vertical.Small()
            FormTextField(
                label = { Text("Duration") },
                value = data.duration,
                onValueChange = onDurationChanged
            )
            Spacer.Vertical.Medium()
            AppButton(onClick = onCalculateClick, text = "Calculate")
            Spacer.Vertical.Large()
            Text.Default(text = "Your lend = ${data.result}")
        }
    }
}