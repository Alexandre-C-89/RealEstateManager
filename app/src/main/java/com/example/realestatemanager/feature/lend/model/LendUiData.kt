package com.example.realestatemanager.feature.lend.model

import androidx.compose.ui.text.input.TextFieldValue

data class LendUiData(
    val totalLoanAmount: TextFieldValue = TextFieldValue(),
    val contribution: TextFieldValue = TextFieldValue(),
    val rate: TextFieldValue = TextFieldValue(),
    val duration: TextFieldValue = TextFieldValue(),
    val result: String = ""
)
