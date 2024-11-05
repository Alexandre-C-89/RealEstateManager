package com.example.realestatemanager.designsystem.button

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.realestatemanager.designsystem.Blue
import com.example.realestatemanager.designsystem.LightGrey
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.White

@Composable
fun AppButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    text: String
){
    Button(
        onClick = onClick,
        enabled = enabled,
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            contentColor = White,
            containerColor = Blue,
            disabledContentColor = White,
            disabledContainerColor = LightGrey
            ),
        contentPadding = PaddingValues(
            start = 8.dp,
            top = ButtonDefaults.ContentPadding.calculateTopPadding(),
            end = 8.dp,
            bottom = ButtonDefaults.ContentPadding.calculateBottomPadding()
        ),
        //content: @Composable RowScope.() -> Unit
    ) {
        Text(text = text)
    }
}

@Preview
@Composable
fun AppButtonPreview(){
    RealEstateManagerTheme {
        AppButton(
            onClick = {},
            enabled = false,
            text = "App Button"
        )
    }
}