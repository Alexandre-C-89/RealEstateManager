package com.example.realestatemanager.designsystem.textfield

import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.ui.Default
import com.example.realestatemanager.designsystem.ui.Shapes


@Composable
fun TextfieldItem(
    value: String,
    onValueChange: TextFieldValue,
    error: Boolean,
    enabled: Boolean,
    label: String,
    placeholder: String,
){
    /*OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        enabled = enabled,
        label =  label,
        placeholder = placeholder
    )*/
}

@Preview
@Composable
fun TextfieldItemPreview(){
    RealEstateManagerTheme {
        TextfieldItem(
            value = "",
            onValueChange = TextFieldValue(),
            error = false,
            enabled = true,
            label =  "label",
            placeholder = "placeholder"
        )
    }
}