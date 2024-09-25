package com.example.realestatemanager.designsystem.textfield

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.Grey
import com.example.realestatemanager.designsystem.LightBlue
import com.example.realestatemanager.designsystem.LightGrey
import com.example.realestatemanager.designsystem.White

@Composable
fun FormTextField(
    value: TextFieldValue,
    label: @Composable (() -> Unit)? = null,
    onValueChange: (TextFieldValue) -> Unit = {},
) {
    val focusManager = LocalFocusManager.current
    OutlinedTextField(
        label = label,
        value = value,
        onValueChange = onValueChange,
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
            focusedContainerColor = White
        )
    )
}

@Preview
@Composable
fun FormTextFieldPreview() {
    FormTextField(
        label = { Text(text = "qfds") },
        value = TextFieldValue("DSDLFK,"),
        onValueChange = { TextFieldValue("slkdfhu") }
    )
}