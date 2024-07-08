package com.example.realestatemanager.property

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.example.realestatemanager.designsystem.AppScaffold
import com.example.realestatemanager.designsystem.RealEstateManagerTheme
import com.example.realestatemanager.designsystem.bar.TopBar
import com.example.realestatemanager.designsystem.textfield.TextfieldItem

@Composable
fun CreatePropertyRoute(

) {
    CreatePropertyScreen(

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreatePropertyScreen(

) {
    val context = LocalContext.current
    var expanded by remember { mutableStateOf(false) }
    AppScaffold(
        topBar = { TopBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                DropdownMenuItem(
                    text = { Text("Load") },
                    onClick = { Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show() })
                DropdownMenuItem(
                    text = { Text("Save") },
                    onClick = { Toast.makeText(context, "Save", Toast.LENGTH_SHORT).show() })
            }
            TextfieldItem(
                value =,
                onValueChange =,
                error =,
                label =,
                placeholder =
            )
        }
    }
}

@Preview
@Composable
fun CreatePropertyScreenPreview() {
    RealEstateManagerTheme {
        CreatePropertyScreen(

        )
    }
}