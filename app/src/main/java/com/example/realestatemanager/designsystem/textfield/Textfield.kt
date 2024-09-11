package com.example.realestatemanager.designsystem.textfield

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.realestatemanager.designsystem.Black
import com.example.realestatemanager.designsystem.LightGrey
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.Theme
import com.example.realestatemanager.designsystem.fonts
import com.example.realestatemanager.designsystem.ui.Spacer


@Composable
fun FormTextField(
    modifier: Modifier = Modifier,
    textValue: TextFieldValue,
    hint: String,
    label: String,
    error: String? = null,
    onValueChange: (TextFieldValue) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    enabled: Boolean = true
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    Label(text = label)
    Spacer.Vertical.Small()
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = textValue,
        onValueChange = onValueChange,
        colors = Colors(),
        placeholder = { PlaceHolder(text = hint) },
        trailingIcon = trailingIcon,
        singleLine = true,
        maxLines = 1,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        visualTransformation = visualTransformation,
        isError = error != null,
        enabled = enabled
    )
}

@Composable
private fun Label(text: String) {
    Text(
        text = text,
        modifier = Modifier.fillMaxWidth(),
        fontSize = 14.sp,
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        color = LightGrey,
        textAlign = TextAlign.Start
    )
}

@Composable
private fun PlaceHolder(text: String) {
    Text(
        text = text,
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        fontFamily = fonts,
        color = Black
    )
}

@Composable
private fun Colors() = OutlinedTextFieldDefaults.colors(
    focusedTextColor = Theme.colorPalette.textPrimary,
    unfocusedTextColor = Theme.colorPalette.textPrimary,
    focusedBorderColor = Color.Transparent,
    unfocusedBorderColor = Color.Transparent,
    focusedContainerColor = Theme.colorPalette.surfaceSecondary,
    unfocusedContainerColor = Theme.colorPalette.surfaceSecondary,
    cursorColor = Theme.colorPalette.textPrimary,
    disabledContainerColor = Theme.colorPalette.surfaceSecondary,
    disabledTextColor = Theme.colorPalette.textSecondary,
    disabledBorderColor = Theme.colorPalette.surfaceSecondary
)

@Preview
@Composable
private fun Preview() {
    FormTextField(
        textValue = TextFieldValue("Default value"),
        hint = "Hint",
        label = "Form Text Field",
        enabled = true
    )
}

@Preview
@Composable
fun TextfieldItemPreview() {
    RealEstateManagerTheme {
        FormTextField(
            textValue = TextFieldValue("Default value"),
            hint = "type",
            label = "Type"
        )
    }
}