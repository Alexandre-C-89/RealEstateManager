package com.example.realestatemanager.designsystem.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun FormTextField(
    modifier: Modifier,
    title: String,
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit = {},
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    Column(
        modifier = Modifier.widthIn(max = 300.dp, min = 150.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text.Default(text = title)
        Spacer.Vertical.Small()
        OutlinedTextField(
            modifier = modifier,
            value = value,
            onValueChange = onValueChange,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            shape = RoundedCornerShape(6.dp),
            singleLine = true
        )
    }
}