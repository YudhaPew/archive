package com.example.list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.list.ui.theme.ListTheme
import com.example.list.ui.screens.ArchiveScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ListTheme {
                // A surface container using the 'background' color from the theme
                ArchiveScreen()
            }
        }
    }
}
