package com.example.bottonnav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.bottonnav.ui.theme.BottonNavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BottonNavTheme {
                mainScreen();


            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottonNavTheme {
        mainScreen();


    }
}

