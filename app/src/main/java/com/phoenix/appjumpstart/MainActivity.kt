package com.phoenix.appjumpstart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.phoenix.appjumpstart.ui.navigation.AppNavigation
import com.phoenix.appjumpstart.ui.theme.AppJumpstartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppJumpstartTheme {
                AppNavigation()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppJumpstartPreview() {
    AppJumpstartTheme {
        AppNavigation()
    }
}