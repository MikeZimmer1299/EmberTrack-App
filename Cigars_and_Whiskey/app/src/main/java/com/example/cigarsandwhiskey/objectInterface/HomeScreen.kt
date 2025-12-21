package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

//class HomeScreen : ComponentActivity(){
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContent {
//            CigarsAndWhiskeyTheme {
//                HomeScreenView()
//            }
//        }
//
//    }
//}

@Composable
@Preview
fun HomeScreen(){

    Text(text= "Welcome to the Home Page")

}