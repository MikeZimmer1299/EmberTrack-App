package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.cigarsandwhiskey.ui.theme.*


// TODO: Notes on key params
//  elevation: adds a shadow to the component that makes it appear elevated above the background
//  colors: uses the CardColors type to set default color of both container and any children
//  enabled: if you pass false for this param, card appears as disabled and does not respond
//  onClick: Card does not *usually* accept click events. This implementation is to overload

@Composable
@Preview
fun HomeScreen(){

//    Text(text= "Welcome to the Home Page")

    // TODO: Need to think about what type of information should be on the home screen

    // TODO: Within Card, since I plan to be able to scroll, I will need
    //  to use the `.verticalScroll(rememberScrollState())` modifier
    // https://developer.android.com/develop/ui/compose/touch-input/pointer-input/scroll
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp, 0.dp)
            .verticalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(
//            containerColor = lushForestGreenDark
//            containerColor = Color(0xFF2b3a3f)
//            containerColor = Color(0xFF554e4a)
//            containerColor = Color(0xFF927E83)
            containerColor = Color(0xFF37534E)
//            containerColor = Color(0xFFC8BCAB)
        )
        // TODO: look at Theme.kt to figure out what each color is getting its params from
    ){
        //  Within this card, I think, is where the content for all pages
        //  should end up going.

        // Row for each object desired on the home screen
        // TODO: May make these, instead of rows, as other cards? Example below of elevated card
        ElevatedCard(
            onClick = {},
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
                containerColor = lushForestGreenDark
            )
        ) {
//            Text(text = "This is where the most recent cigar review is going")
        }

        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGreenDark
            )
        ) {
//            Text(text = "This is where the most recent whiskey review is going")
        }

        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGreenDark
            )
        ) {
//            Text(text = "This is where the list of the user's highest rated cigars is going")
        }

        ElevatedCard(
            modifier = Modifier
                .padding(
                    10.dp, // left
                    10.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 260.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGreenDark
            )
        ) {
//            Text(text = "This is potentially where a random whiskey/cigar brand will " +
//                    "go, to give option for user to learn")
        }
    }

}