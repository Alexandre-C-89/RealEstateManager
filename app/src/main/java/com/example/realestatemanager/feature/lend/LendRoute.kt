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
                modifier = Modifier
                    .width(100.dp)
                    .height(40.dp),
                value = data.totalLoanAmount,
                title = "total loan amount",
                onValueChange = onTotalLoanAmountChanged,
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
                    .width(100.dp)
                    .height(40.dp),
                value = data.contribution,
                title = "contribution",
                onValueChange = onContributionChanged,
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
                modifier = Modifier
                    .width(50.dp)
                    .height(40.dp),
                value = data.rate,
                title = "rate",
                onValueChange = onRateChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer.Vertical.Small()
            FormTextField(
                modifier = Modifier
                    .width(50.dp)
                    .height(40.dp),
                value = data.duration,
                title = "duration",
                onValueChange = onDurationChanged,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                )
            )
            Spacer.Vertical.Medium()
            AppButton(onClick = onCalculateClick, text = "Calculate")
            Spacer.Vertical.Large()
            Text.Default(text = "Your lend = ${data.result}")
        }
    }
}