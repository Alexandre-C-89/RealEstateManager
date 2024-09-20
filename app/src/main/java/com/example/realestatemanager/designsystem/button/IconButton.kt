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
            contentDescription = "menu button"
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
            contentDescription = "add button"
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
            contentDescription = " edit button"
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
            contentDescription = "search button"
        )
    }
}

@Composable
fun BackIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back button"
        )
    }
}

@Composable
fun HomeIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_home),
            contentDescription = "home button"
        )
    }
}

@Composable
fun MapIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_map),
            contentDescription = "map button"
        )
    }
}

@Composable
fun LendIconButton(
    onClick: () -> Unit
){
    IconButton(onClick = { onClick() }) {
        Icon(
            painter = painterResource(id = R.drawable.ic_lend),
            contentDescription = "lend button"
        )
    }
}