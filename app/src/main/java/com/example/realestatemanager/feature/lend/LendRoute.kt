package com.example.realestatemanager.feature.lend

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.DarkBlue
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
    isExpandedScreen: Boolean,
    viewModel: LendViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val uiData by viewModel.data.collectAsStateWithLifecycle()

    LendScreen(
        isExpandedScreen = isExpandedScreen,
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
    isExpandedScreen: Boolean,
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
        if (!isExpandedScreen){
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                Title.Default(text = "Choose option for lend")
                Spacer.Vertical.Default()
                FormTextField(
                    modifier = Modifier,
                    label = { Text("Total Loan Amount") },
                    value = data.totalLoanAmount,
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
                    modifier = Modifier,
                    label = { Text("Contribution") },
                    value = data.contribution,
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
                    modifier = Modifier,
                    label = { Text("Rate") },
                    value = data.rate,
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
                    modifier = Modifier,
                    label = { Text("Duration") },
                    value = data.duration,
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
        } else {
            Column(
                Modifier.fillMaxSize().padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Title.Default(text = "Choose option for lend")
                Spacer.Vertical.Medium()
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    FormTextField(
                        modifier = Modifier.width(300.dp),
                        label = { Text("Total Loan Amount") },
                        value = data.totalLoanAmount,
                        onValueChange = onTotalLoanAmountChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    FormTextField(
                        modifier = Modifier.width(300.dp),
                        label = { Text("Contribution") },
                        value = data.contribution,
                        onValueChange = onContributionChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    FormTextField(
                        modifier = Modifier.width(300.dp),
                        label = { Text("Rate") },
                        value = data.rate,
                        onValueChange = onRateChanged,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Next)
                            }
                        )
                    )
                    FormTextField(
                        modifier = Modifier.width(300.dp),
                        label = { Text("Duration") },
                        value = data.duration,
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
                }
                Spacer.Vertical.Large()
                AppButton(onClick = onCalculateClick, text = "Calculate")
                Spacer.Vertical.Large()
                Text(
                    text = data.result,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = DarkBlue
                )
            }
        }
    }
}