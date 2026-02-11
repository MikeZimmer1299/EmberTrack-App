package com.example.cigarsandwhiskey.objectInterface

import android.R
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cigarsandwhiskey.ui.theme.lushForestGrassLight
import com.example.cigarsandwhiskey.ui.theme.lushForestGreenDark

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

    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
            containerColor = lushForestGreenDark
        )

    ){
//        Text(text = "Welcome to the My Cigars Page")

        // TODO: First starting off as if the user only has one humidor, will implement
        //  more than one humidor once the single humidor object is in working order

        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    45.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
            Text(text = "This is where the most recent cigar added to the humidor will go" +
                    " or it will be the option to choose which humidor you wish to view." +
                    " This is entirely dependent on if the user has one or more humidors.")
        }

    }

    // TODO: This button will allow a user to add a new cigar(s) to their collection
    ExtendedFloatingActionButton(
        modifier = Modifier
            .padding(
                305.dp, // left
                920.dp,
                0.dp, // right
                0.dp
            ),
        containerColor = lushForestGrassLight,
        onClick = {},
        icon = { Icon(Icons.Filled.Add, "Add Cigar Button") },
        text = { Text(text = "Add Cigar(s)")}
    )

}