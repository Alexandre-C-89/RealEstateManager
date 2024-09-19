package com.example.realestatemanager.designsystem.textfield

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.ui.Spacer
import com.example.realestatemanager.designsystem.ui.text.Text

@Composable
fun FormTextField(
    title: String,
    textValue: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit = {},
    label: @Composable (() -> Unit)? = null
) {
    Column(
        modifier = Modifier.widthIn(max = 300.dp, min = 150.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text.Default(text = title)
        Spacer.Vertical.Small()
        TextField(
            value = textValue,
            onValueChange = onValueChange,
            label = label
        )
    }
}

@Preview
@Composable
fun FormTextFieldPreview() {
    FormTextField(
        title = "Title",
        textValue = TextFieldValue("Form"),
        onValueChange = { TextFieldValue("onValueChange") },
        label = {
            Text.Small("label")
        }
    )
}