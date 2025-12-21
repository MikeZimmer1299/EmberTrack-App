package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

// it has the ` ` around object because my naming convention is NOT good for now
//class MyCigarsActivity : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
//
//        setContent {
//            CigarsAndWhiskeyTheme{
//                MyCigarsScreen()
//            }
//        }
//    }
//}

@Composable
@Preview
fun MyCigarsScreen(){

    Text("Welcome to My Cigars Page", modifier = Modifier.padding(0.dp))

}