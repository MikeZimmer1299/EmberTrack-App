package com.example.cigarsandwhiskey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.core.view.WindowCompat

import com.example.cigarsandwhiskey.ui.theme.CigarsAndWhiskeyTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        WindowCompat.setDecorFitsSystemWindows(window, false)

        val database = AppDatabase.getDatabase(this)

        setContent {
            CigarsAndWhiskeyTheme {
                AppRoot(database)
            }
        }
    }
}


@Composable
fun AppRoot(database: AppDatabase){ // Maybe a temporary name, may change later

    Navigation(database)
    // By calling Navigation(), it sets startDestination
    //  to "home" screen

}