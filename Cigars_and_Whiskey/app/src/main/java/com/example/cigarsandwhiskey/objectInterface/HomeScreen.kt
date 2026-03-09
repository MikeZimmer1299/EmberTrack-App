package com.example.cigarsandwhiskey.objectInterface

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cigarsandwhiskey.objects.MyCigars
import com.example.cigarsandwhiskey.objects.MyWhiskey
import com.example.cigarsandwhiskey.objects.filterList
import com.example.cigarsandwhiskey.objects.proofToPercent
import com.example.cigarsandwhiskey.ui.theme.*


// TODO: Notes on key params
//  elevation: adds a shadow to the component that makes it appear elevated above the background
//  colors: uses the CardColors type to set default color of both container and any children
//  enabled: if you pass false for this param, card appears as disabled and does not respond
//  onClick: Card does not *usually* accept click events. This implementation is to overload

@Composable
@Preview
fun HomeScreen(){

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
            containerColor = lushForestGreenDark
        )
        // TODO: look at Theme.kt to figure out what each color is getting its params from
    ){
        //  Within this card, I think, is where the content for all pages
        //  should end up going.

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp, 30.dp, 0.dp, 0.dp),

//        colors = CardDefaults.cardColors()
        ) {
            Text(text = "Home", fontSize = 40.sp,
                modifier = Modifier
                    .drawBehind{
                        val strokeWidthPx = 3.dp.toPx()
                        drawLine(
                            color = Color.Black,
                            strokeWidth = strokeWidthPx,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height)
                        )
                    },
//                color = lushForestGrassLight,
                fontWeight = FontWeight.Bold
            )
        }

        // Row for each object desired on the home screen
        // TODO: May make these, instead of rows, as other cards? Example below of elevated card
        //  This is the correct move
        ElevatedCard(
//            onClick = {},
            modifier = Modifier
                .padding(
                    10.dp, // left
                    15.dp,
                    10.dp, // right
                    5.dp
                )
//                .background(color = Color.Green)
                .size(width = 480.dp, height = 200.dp),
            colors = CardDefaults.cardColors(
                containerColor = lushForestGrassLight
            )
        ) {
//            Text(text = "Your Most Recent Cigar Reviews")

            // TODO: Examples for refreshing memory below on how objects and things are made/used
            //  with their content. Things like this will be moved into their own functions later on
//            var temp1 = MyCigars("Tatuaje", "Boris Karloff", "Nicaragua", 5.75f, 52)
//            var temp2 = MyCigars("Tatuaje", "PCA 2025","Nicaragua", 5.62f, 54)
//            var temp3 = MyCigars("Cavalier Geneve", "Green Jacket 2025", "Honduras", 7.00f, 47)
//            val tempList = listOf<MyCigars>(
//                temp1, temp2, temp3
//            )
//            filterList(tempList)

//            var tempWhiskey = MyWhiskey("Maker's Mark", "46", 94f)
//            val proofNumber = proofToPercent(tempWhiskey.proof)

//            Text(text = "The most recent review was for $temp1")
//            Text(text = "The second most recent review is $temp2")
//            Text(text = "Will this work? Ring gauge: ${temp1.ringGauge}")
//            Text(text = "Favorite cigar is $temp3")

//            for (cigar in tempList){
//                Text( text = "The cigar of choice is the: ${cigar.cigarName}\n")
//            }


            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        0.dp
                    )
            ){
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 225.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = lushForestGreenDark
                    )
                ) {
                    Text(text = "This is test text")
                }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 225.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { Text(text = "This is a test") }
//                Card(
////                    onClick = {},
//                    modifier = Modifier
//                        .size(width = 155.dp, height = 180.dp)
//                        .padding(
//                            5.dp, // left
//                            0.dp,
////                            0.dp, // right
////                            0.dp
//                        ),
//                    colors = CardDefaults.cardColors(
//                        containerColor = earthForestMediumDark
//                    )
//                ) { }
            }


        }

        ElevatedCard(
//            onClick = {},
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
                containerColor = lushForestGrassLight
            )
        ) {
//            Text(text = "This is where the most recent whiskey review is going")

            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        0.dp
                    )
            ){
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
            }
        }

        ElevatedCard(
//            onClick = {},
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
                containerColor = lushForestGrassLight
            )
        ) {
//            Text(text = "This is where the list of the user's highest rated cigars is going")

            Row(
                modifier = Modifier
                    .padding(
                        5.dp, // left
                        10.dp,
                        5.dp, // right
                        0.dp
                    )
            ){
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 150.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
                Card(
//                    onClick = {},
                    modifier = Modifier
                        .size(width = 155.dp, height = 180.dp)
                        .padding(
                            5.dp, // left
                            0.dp,
//                            0.dp, // right
//                            0.dp
                        ),
                    colors = CardDefaults.cardColors(
                        containerColor = earthForestMediumDark
                    )
                ) { }
            }

        }

        ElevatedCard(
//            onClick = {},
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
                containerColor = lushForestGrassLight
            )
        ) {
            Text(text = "This is potentially where a random whiskey/cigar brand will " +
                    "go, to give option for user to learn", color = Color.Black)
            // TODO: This text is currently the same color at "lushForestGrassLight"
            //  until I changed the color manually. Will need to keep in mind for future
        }
    }

}