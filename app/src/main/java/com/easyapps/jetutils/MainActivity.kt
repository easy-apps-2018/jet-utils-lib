package com.easyapps.jetutils

import android.os.*
import androidx.activity.*
import androidx.activity.compose.*
import com.easyapps.jetutils.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetUtilsAppTheme {

            }
        }
    }
}