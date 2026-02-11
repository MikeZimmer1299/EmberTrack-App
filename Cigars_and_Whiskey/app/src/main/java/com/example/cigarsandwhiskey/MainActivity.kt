package com.example.cigarsandwhiskey

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.cigarsandwhiskey.ui.theme.CigarsAndWhiskeyTheme
//import com.example.cigarsandwhiskey.ui.theme.MainView // in /theme folder
// May remove the below import at a later time

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        // TODO: Delete this list and val object later
//        val people = listOf(
//            Person("DJ", "Malone", 52),
//            Person("DJ", "Malone", 35),
//            Person("DJ", "Smith", 26),
//            Person("DJ", "Malone", 21),
//            Person("DJ", "Malone", 19)
//        )
//        val peopleFiltered = people.filter { it.age >= 21 && it.lastName == "Smith" }

        setContent {
            CigarsAndWhiskeyTheme {
                AppRoot()
            }
        }
    }
}


@Composable
fun AppRoot(){ // May be a temporary name, may change later

    Navigation()
    // By calling Navigation(), it sets startDestination
    //  to "home" screen

}

// TODO: Delete at later time
//@Composable
//fun CardView(person: Person){ // param: Type
//    Card(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(12.dp)
//    ) {
//        Row() {
//            Image(painter = painterResource(id = R.drawable.outline_person_24),
//                contentDescription = "Photo of person",
//                modifier = Modifier
//                    .width(100.dp)
//                    .height(100.dp)
//            )
//            Column{
//                Text(
//                    text = "First Name: " + person.firstName,
//                    modifier = Modifier.padding(top = 10.dp),
//                )
//                Text(
//                    text = "Last Name: " + person.lastName,
//                    modifier = Modifier.padding(0.dp),
//                )
//                Text(
//                    text = "Age: " + person.age,
//                    modifier = Modifier.padding(0.dp),
//                )
//            }
//
//        }
//
//    }
//}