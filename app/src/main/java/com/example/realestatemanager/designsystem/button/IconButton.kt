package com.example.realestatemanager.designsystem.button

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import com.example.realestatemanager.R

@Composable
fun MenuIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_menu),
            contentDescription = "button"
        )
    }
}

@Composable
fun AddIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_add),
            contentDescription = "button"
        )
    }
}

@Composable
fun EditIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_edit),
            contentDescription = "button"
        )
    }
}

@Composable
fun SearchIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_search),
            contentDescription = "button"
        )
    }
}