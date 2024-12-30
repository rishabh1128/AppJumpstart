package com.phoenix.appjumpstart.ui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ErrorDialog(
    message: String,
    onClick: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onClick,
        title = {
            Text(text = "Error")
        },
        text = {
            Text(text = message)
        },
        confirmButton = {
            Button(onClick = onClick) {
                Text("OK")
            }
        }
    )
}