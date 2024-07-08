package com.example.realestatemanager.designsystem.checkbox

import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.White

@Composable
fun checkboxItem(
    checked: Boolean,
    onCheckedChange: Boolean,
    enabled: Boolean
){
    Checkbox(
        checked = checked,
        onCheckedChange = { onCheckedChange },
        enabled = enabled,
        colors = CheckboxDefaults.colors(
            checkedColor = Blue,
            checkmarkColor = White
        )
    )
}

@Preview
@Composable
fun CheckboxItemPreview(){
    RealEstateManagerTheme {
        checkboxItem(
            checked = true,
            onCheckedChange = true,
            enabled = true
        )
    }
}